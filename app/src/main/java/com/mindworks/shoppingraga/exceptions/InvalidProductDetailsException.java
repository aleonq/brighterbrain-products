package com.mindworks.shoppingraga.exceptions;

/**
 * Created by taru on 10/15/2017.
 */

public class InvalidProductDetailsException extends RuntimeException {
    private int errorCode;

    public InvalidProductDetailsException(int errorCode) {
        super("Product details are invalid");
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
