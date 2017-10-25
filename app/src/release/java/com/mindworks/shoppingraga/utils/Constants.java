package com.mindworks.shoppingraga.utils;

/**
 * Created by taru on 10/15/2017.
 */

public class Constants {

    public static final String DATABASE_NAME = "product_details";
    public static final String KEY_PRODUCT = "product";
    public static final String KEY_PRODUCT_DETAILS_EDITABLE = "editable";
    public static final String STATIC_DATA_FILE_NAME = "static_data.txt";

    public interface ErrorCodes {
        int PRODUCT_DETAILS_INCORRECT = 1001;
        int PRODUCT_NAME_EMPTY = 1002;
        int PRODUCT_DESC_EMPTY = 1003;
        int PRODUCT_REG_PRICE_EMPTY = 1004;
        int PRODUCT_SALE_PRICE_EMPTY = 1005;
        int PRODUCT_REG_PRICE_NAN = 1006;
        int PRODUCT_SALE_PRICE_NAN = 1007;
        int PRODUCT_REG_PRICE_NEG = 1008;
        int PRODUCT_SALE_PRICE_NEG = 1009;
    }

    public interface SuccessCodes {
        int PRODUCT_UPDATED = 2001;
        int PRODUCT_CREATED = 2002;
    }
}
