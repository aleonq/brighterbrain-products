package com.mindworks.shoppingraga.productdetail.mvp;

import android.bluetooth.le.AdvertiseData;
import android.content.Context;
import android.text.TextUtils;

import com.mindworks.shoppingraga.R;
import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.exceptions.InvalidProductDetailsException;
import com.mindworks.shoppingraga.utils.Constants;

/**
 * Created by taru on 10/15/2017.
 */

public class ProductDetailsPresenter implements ProductDetailsMvp.Presenter {

    ProductDetailsMvp.View view;
    private ProductDetailsMvp.Model model;

    public ProductDetailsPresenter(ProductDetailsMvp.Model model) {
        this.model = model;
        model.setPresenter(this);
    }

    @Override
    public void setView(ProductDetailsMvp.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        Product product = model.getSelectedProduct();
        view.displayProduct(product);
        view.setEditable(model.isProductDetailsEditable());
    }

    @Override
    public void onFabClicked(boolean editable, Product product) {
        if (editable) {
            try {
                validateProduct(product);
                long result = -1;
                if (product.getId() > 0) {
                    result = model.updateProduct(product);
                    view.displayMessage(Constants.SuccessCodes.PRODUCT_UPDATED);
                } else {
                    result = model.saveProduct(product);
                    view.displayMessage(Constants.SuccessCodes.PRODUCT_CREATED);
                }
                if (result > 0) {
                    model.setProductDetailsEditable(false);
                    view.setEditable(false);
                    view.exitActivity();
                }
            } catch (InvalidProductDetailsException e) {
                view.displayMessage(e.getErrorCode());
            }
        } else {
            model.setProductDetailsEditable(true);
            view.setEditable(true);
        }
    }

    private void validateProduct(Product product) throws InvalidProductDetailsException {
        if (product == null)
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_DETAILS_INCORRECT);
        if (TextUtils.isEmpty(product.getName()))
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_NAME_EMPTY);
        if (TextUtils.isEmpty(product.getDescription()))
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_DESC_EMPTY);
        if (TextUtils.isEmpty(product.getRegularPrice()))
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_REG_PRICE_EMPTY);
        if (TextUtils.isEmpty(product.getSalePrice()))
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_SALE_PRICE_EMPTY);
        try {
            double price = Double.parseDouble(product.getRegularPrice());
            if (price < 0) {
                throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_REG_PRICE_NEG);
            }
        } catch (NumberFormatException e) {
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_REG_PRICE_NAN);
        }
        try {
            double price = Double.parseDouble(product.getSalePrice());
            if (price < 0) {
                throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_SALE_PRICE_NEG);
            }
        } catch (NumberFormatException e) {
            throw new InvalidProductDetailsException(Constants.ErrorCodes.PRODUCT_SALE_PRICE_NAN);
        }
    }
}