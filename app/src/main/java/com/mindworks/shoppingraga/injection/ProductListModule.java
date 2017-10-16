package com.mindworks.shoppingraga.injection;

import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListModel;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListMvp;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 10/16/2017.
 */
@Module(includes = {AppDatabaseModule.class})
public class ProductListModule {
    @Provides
    public ProductListMvp.Presenter getProductListPresenter(ProductListMvp.Model model) {
        return new ProductListPresenter(model);
    }

    @Provides
    public ProductListMvp.Model getProductListModel(AppDatabase db) {
        return new ProductListModel(db);
    }
}
