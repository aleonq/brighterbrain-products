package com.mindworks.shoppingraga.injection;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;
import com.mindworks.shoppingraga.utils.Constants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 10/16/2017.
 */

@Module(includes = {AppDatabaseModule.class, AppModule.class})
public class StaticDataInsertModule {

    @Provides
    public ArrayList<Product> getStaticProductData(AppDatabase appDatabase, Context context) {
        ArrayList<Product> products = new ArrayList<Product>();
        if (isDataInserted(appDatabase)) {
            return products;
        }
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(Constants.STATIC_DATA_FILE_NAME, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        products = new Gson().fromJson(returnString.toString(), new TypeToken<List<Product>>() {
        }.getType());
        for (Product task : products) {
            Log.d("product json : ", task.toString());
        }
        return products;
//      appDatabase.productDao().addProduct(product);
    }

    private boolean isDataInserted(AppDatabase appDatabase) {
        List<Product> products = appDatabase.productDao().loadAllProducts();
        if (products != null && products.size() > 0) return true;
        return false;
    }
}