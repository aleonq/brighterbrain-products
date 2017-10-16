package com.mindworks.shoppingraga.productdetail.mvp;

import com.mindworks.shoppingraga.datastore.beans.Product;

/**
 * Created by taru on 10/15/2017.
 */

public class ProductDetailsMvp {
    public interface Model {

        void setPresenter(Presenter presenter);

        Product getSelectedProduct();

        boolean isProductDetailsEditable();

        void setProductDetailsEditable(boolean isEditable);

        int updateProduct(Product product);

        long saveProduct(Product product);
    }

    public interface View {
        void onViewCreated();

        void displayProduct(Product product);

        void setEditable(boolean productDetailsEditable);

        void displayMessage(int message);

        void exitActivity();
    }

    public interface Presenter {
        void setView(View view);

        void loadData();

        void onFabClicked(boolean editable, Product product);
    }
}
