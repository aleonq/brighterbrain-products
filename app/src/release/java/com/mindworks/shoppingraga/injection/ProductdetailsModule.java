package com.mindworks.shoppingraga.injection;

import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsModel;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsMvp;
import com.mindworks.shoppingraga.productdetail.mvp.ProductDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 10/16/2017.
 */

@Module(includes = {AppDatabaseModule.class})
public class ProductdetailsModule {
    @Provides
    public ProductDetailsMvp.Presenter getProductDetailsPresenter(ProductDetailsMvp.Model model) {
        return new ProductDetailsPresenter(model);
    }

    @Provides
    public ProductDetailsMvp.Model getProductDetailsModel(AppDatabase db) {
        return new ProductDetailsModel(db);
    }
}
