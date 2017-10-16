package com.mindworks.shoppingraga.productdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.mindworks.shoppingraga.App;
import com.mindworks.shoppingraga.R;
import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsMvp;
import com.mindworks.shoppingraga.utils.NotificationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by taru on 10/15/2017.
 */

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailsMvp.View {

    private static final String TAG = ProductDetailActivity.class.getSimpleName();

    @Inject
    ProductDetailsMvp.Presenter presenter;
    private Product product;
    private boolean isEditable;

    @BindView(R.id.vs_details_holder)
    ViewSwitcher vsDetailsHolder;

    @BindView(R.id.tv_product_name)
    TextView tvName;
    @BindView(R.id.et_product_name)
    EditText etName;

    @BindView(R.id.tv_product_id)
    TextView tvId;

    @BindView(R.id.tv_product_description)
    TextView tvDescription;
    @BindView(R.id.et_product_description)
    EditText etDescription;

    @BindView(R.id.tv_product_regular_price)
    TextView tvRegularPrice;
    @BindView(R.id.et_product_regular_price)
    EditText etRegularPrice;

    @BindView(R.id.tv_product_sale_price)
    TextView tvSalePrice;
    @BindView(R.id.et_product_sale_price)
    EditText etSalePrice;

    @BindView(R.id.fab)
    FloatingActionButton fab_action;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

//        presenter = ((App) getApplication()).getProductDetailsPresenter();
        ((App)getApplication()).getAppComponent().inject(this);
        presenter.setView(this);
        onViewCreated();
    }

    @Override
    public void onViewCreated() {
        presenter.loadData();
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        long id = (product == null || product.getId() < 0) ? 0 : product.getId();
        product = new Product(id, etName.getText().toString(),
                etDescription.getText().toString(),
                etRegularPrice.getText().toString(),
                etSalePrice.getText().toString(),
                ""
        );
        presenter.onFabClicked(isEditable, product);
    }

    @Override
    public void displayProduct(Product product) {
        this.product = product;
        if (product != null) {
            tvName.setText(product.getName());
            tvId.setText("" + product.getId());
            tvDescription.setText("" + product.getDescription());
            tvRegularPrice.setText("" + product.getRegularPrice());
            tvSalePrice.setText("" + product.getSalePrice());
            etName.setText(product.getName());
            etDescription.setText("" + product.getDescription());
            etRegularPrice.setText("" + product.getRegularPrice());
            etSalePrice.setText("" + product.getSalePrice());
        } else {
            tvName.setText("");
            tvId.setText("");
            tvDescription.setText("");
            tvRegularPrice.setText("");
            tvSalePrice.setText("");
            etName.setText("");
            etDescription.setText("");
            etRegularPrice.setText("");
            etSalePrice.setText("");
        }
    }

    @Override
    public void setEditable(boolean productDetailsEditable) {
        isEditable = productDetailsEditable;
        if (isEditable) {
            fab_action.setImageResource(R.drawable.btn_save);
            if (vsDetailsHolder.getNextView().getId() == R.id.ll_details_edit) {
                vsDetailsHolder.showNext();
            }
        } else {
            fab_action.setImageResource(R.drawable.btn_edit);
            if (vsDetailsHolder.getNextView().getId() == R.id.ll_details_no_edit) {
                vsDetailsHolder.showNext();
            }
        }
    }

    @Override
    public void displayMessage(int messageCode) {
        NotificationUtils.INSTANCE.showMessage(messageCode, vsDetailsHolder, getApplication().getApplicationContext());
    }

    @Override
    public void exitActivity() {
        finish();
    }
}