package com.mindworks.shoppingraga.productlisting.mvp;

import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;
import com.mindworks.shoppingraga.utils.Constants;
import com.mindworks.shoppingraga.utils.ObjectPool;

import java.util.List;

/**
 * Created by taru on 10/15/2017.
 */

public class ProductListModel implements ProductListMvp.Model {

    private ProductListMvp.Presenter presenter;
    private AppDatabase appDatabase;
    private ObjectPool objectPool;

    public ProductListModel(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        objectPool = ObjectPool.INSTANCE;
    }

    @Override
    public List<Product> getProductsList() {
        List<Product> products = appDatabase.productDao().loadAllProducts();
        return products;
    }

    @Override
    public void setSelectedProduct(Product product) {
        objectPool.setValue(Constants.KEY_PRODUCT, product);
    }

    @Override
    public void setProductDetailsEditable(boolean isEditable) {
        objectPool.setValue(Constants.KEY_PRODUCT_DETAILS_EDITABLE, isEditable);
    }

    @Override
    public void removeSelectedProduct() {
        objectPool.setValue(Constants.KEY_PRODUCT, null);
    }
}