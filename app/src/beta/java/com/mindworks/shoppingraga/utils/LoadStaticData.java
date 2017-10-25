package com.mindworks.shoppingraga.utils;

import android.content.Context;

import com.google.gson.JsonArray;
import com.mindworks.shoppingraga.App;
import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by taru on 10/16/2017.
 */

public class LoadStaticData {

    @Inject
    AppDatabase database;
    @Inject
    ArrayList<Product> products;

    public void insertStaticData(Context context) {
        ((App) context.getApplicationContext()).getAppComponent().inject(this);
        for (Product p : products) {
            database.productDao().addProduct(p);
        }
    }
}