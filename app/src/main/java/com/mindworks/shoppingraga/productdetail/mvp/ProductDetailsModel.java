package com.mindworks.shoppingraga.productdetail.mvp;

import android.util.Log;

import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;
import com.mindworks.shoppingraga.utils.Constants;
import com.mindworks.shoppingraga.utils.ObjectPool;

/**
 * Created by taru on 10/15/2017.
 */

public class ProductDetailsModel implements ProductDetailsMvp.Model {

    private static final String TAG = ProductDetailsModel.class.getSimpleName();
    private final AppDatabase database;
    ProductDetailsMvp.Presenter presenter;
    ObjectPool objectPool;

    public ProductDetailsModel(AppDatabase database) {
        this.database = database;
        objectPool = ObjectPool.INSTANCE;
    }

    @Override
    public void setPresenter(ProductDetailsMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Product getSelectedProduct() {
        return (Product) objectPool.getValue(Constants.KEY_PRODUCT);
    }

    @Override
    public boolean isProductDetailsEditable() {
        return (Boolean)objectPool.getValue(Constants.KEY_PRODUCT_DETAILS_EDITABLE);
    }

    @Override
    public void setProductDetailsEditable(boolean isEditable) {
        objectPool.setValue(Constants.KEY_PRODUCT_DETAILS_EDITABLE, isEditable);
    }

    @Override
    public int updateProduct(Product product) {
        return database.productDao().updateProduct(product);
    }

    @Override
    public long saveProduct(Product product) {
        return database.productDao().addProduct(product);
    }
}