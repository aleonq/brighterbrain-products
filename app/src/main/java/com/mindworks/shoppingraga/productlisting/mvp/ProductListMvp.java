package com.mindworks.shoppingraga.productlisting.mvp;

import com.mindworks.shoppingraga.datastore.beans.Product;

import java.util.List;

/**
 * Created by taru on 10/15/2017.
 */

public interface ProductListMvp {
    public interface Model {
        List<Product> getProductsList();

        void setSelectedProduct(Product product);

        void setProductDetailsEditable(boolean b);

        void removeSelectedProduct();
    }

    public interface View {
        void displayProducts(List<Product> products);

        void launchProductDetailsActivity();

        void showLocaliationSettings();
    }

    public interface Presenter {
        void setView(View v);

        void loadData();

        void onProductClicked(Product product);

        void onFabClicked();

        void onOptionsSettingsSelected();
    }
}
