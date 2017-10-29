package com.mindworks.shoppingraga.productlisting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import com.mindworks.shoppingraga.App;
import com.mindworks.shoppingraga.R;
import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.productdetail.ProductClickListener;
import com.mindworks.shoppingraga.productdetail.ProductDetailActivity;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListMvp;
import com.mindworks.shoppingraga.utils.NotificationUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ProductListMvp.View, ProductClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    ProductListMvp.Presenter presenter;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Context mContext;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
//        presenter = ((App) getApplication()).getProductListingPresenter();
        ((App)getApplication()).getAppComponent().inject(this);
        presenter.setView(this);
        initUI();
        checkForUpdates();
    }

    private void initUI() {
        //Init recyclerView
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        int spanCount = isLandscape ? 5 : 3;
        mLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        rvProducts.setLayoutManager(mLayoutManager);
        adapter = new ProductsListAdapter(productList, this);
        rvProducts.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    void onFabClicked() {
        presenter.onFabClicked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData();
        checkForCrashes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            presenter.onOptionsSettingsSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayProducts(List<Product> products) {
        productList.clear();
        for (Product p : products) {
            Log.d(TAG, "displayProducts: " + p.toString());
            productList.add(p);

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void launchProductDetailsActivity() {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLocaliationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        PackageManager manager = getPackageManager();
        List<ResolveInfo> info = manager.queryIntentActivities(intent, 0);
        if (info.size() > 0) {
            startActivity(intent);
        } else {
            NotificationUtils.INSTANCE.showMessage(getResources().getString(R.string.no_locale_handler), rvProducts);
        }
    }

    @Override
    public void onProductClicked(Product product) {
        presenter.onProductClicked(product);
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }
}