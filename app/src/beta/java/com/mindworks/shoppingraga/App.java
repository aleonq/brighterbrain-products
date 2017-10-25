package com.mindworks.shoppingraga;

import android.app.Application;
import android.arch.persistence.room.Insert;
import android.content.Context;
import android.util.Log;

import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;
import com.mindworks.shoppingraga.injection.AppModule;
import com.mindworks.shoppingraga.injection.StaticDataInsertModule;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsModel;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsMvp;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsPresenter;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListMvp;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListModel;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListPresenter;
import com.mindworks.shoppingraga.utils.LoadStaticData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

/**
 * Created by taru on 10/15/2017.
 */

public class App extends Application {

    private AppComponent daggerAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        daggerAppComponent = DaggerAppComponent.builder().appModule(new AppModule(getApplicationContext())).build();
//        Log.d("data", loadDataFromAssets("static_data.txt", this));
        new LoadStaticData().insertStaticData(this);
    }

    public AppComponent getAppComponent() {
        return daggerAppComponent;
    }

    public String loadDataFromAssets(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
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
        return returnString.toString();
    }
}