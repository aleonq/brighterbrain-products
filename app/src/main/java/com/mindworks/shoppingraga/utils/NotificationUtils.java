package com.mindworks.shoppingraga.utils;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mindworks.shoppingraga.R;

/**
 * Created by taru on 10/15/2017.
 */

public enum NotificationUtils {
    INSTANCE;

    public void showMessage(int messageCode, View view, Context context) {
        String message = resolveMessageCode(messageCode, context);
        showMessage(message, view);
    }

    public void showMessage(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public String resolveMessageCode(int messsageCode, Context context) {
        String resolvedMessage;
        switch (messsageCode) {
            case Constants.ErrorCodes.PRODUCT_DESC_EMPTY:
                resolvedMessage = context.getString(R.string.err_product_desc_empty);
                break;
            case Constants.ErrorCodes.PRODUCT_DETAILS_INCORRECT:
                resolvedMessage = context.getString(R.string.err_product_details_incorrect);
                break;
            case Constants.ErrorCodes.PRODUCT_NAME_EMPTY:
                resolvedMessage = context.getString(R.string.err_product_name_empty);
                break;
            case Constants.ErrorCodes.PRODUCT_REG_PRICE_EMPTY:
                resolvedMessage = context.getString(R.string.err_product_reg_price_empty);
                break;
            case Constants.ErrorCodes.PRODUCT_REG_PRICE_NAN:
                resolvedMessage = context.getString(R.string.err_product_reg_price_nan);
                break;
            case Constants.ErrorCodes.PRODUCT_REG_PRICE_NEG:
                resolvedMessage = context.getString(R.string.err_product_reg_price_negative);
                break;
            case Constants.ErrorCodes.PRODUCT_SALE_PRICE_EMPTY:
                resolvedMessage = context.getString(R.string.err_product_sale_price_empty);
                break;
            case Constants.ErrorCodes.PRODUCT_SALE_PRICE_NAN:
                resolvedMessage = context.getString(R.string.err_product_sale_price_nan);
                break;
            case Constants.ErrorCodes.PRODUCT_SALE_PRICE_NEG:
                resolvedMessage = context.getString(R.string.err_product_sale_price_negative);
                break;
            case Constants.SuccessCodes.PRODUCT_CREATED:
                resolvedMessage = context.getString(R.string.success_product_created);
                break;
            case Constants.SuccessCodes.PRODUCT_UPDATED:
                resolvedMessage = context.getString(R.string.success_product_updated);
                break;
            default:
                resolvedMessage = context.getString(R.string.err_mgg_code_unresolved);
        }
        return resolvedMessage;
    }
}