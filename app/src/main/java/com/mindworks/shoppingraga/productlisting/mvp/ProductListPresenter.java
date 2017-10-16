package com.mindworks.shoppingraga.productlisting.mvp;

import com.mindworks.shoppingraga.datastore.beans.Product;

/**
 * Created by taru on 10/15/2017.
 */

public class ProductListPresenter implements ProductListMvp.Presenter {

    private final ProductListMvp.Model model;
    private ProductListMvp.View view;

    public ProductListPresenter(ProductListMvp.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ProductListMvp.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        view.displayProducts(model.getProductsList());
    }

    @Override
    public void onProductClicked(Product product) {
        model.setSelectedProduct(product);
        model.setProductDetailsEditable(false);
        view.launchProductDetailsActivity();
    }

    @Override
    public void onFabClicked() {
        model.setProductDetailsEditable(true);
        model.removeSelectedProduct();
        view.launchProductDetailsActivity();
    }

    @Override
    public void onOptionsSettingsSelected() {
        view.showLocaliationSettings();
    }
}