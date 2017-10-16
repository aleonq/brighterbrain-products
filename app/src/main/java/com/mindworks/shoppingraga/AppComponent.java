package com.mindworks.shoppingraga;


import com.mindworks.shoppingraga.injection.AppModule;
import com.mindworks.shoppingraga.injection.ProductListModule;
import com.mindworks.shoppingraga.injection.ProductdetailsModule;
import com.mindworks.shoppingraga.injection.StaticDataInsertModule;
import com.mindworks.shoppingraga.productdetail.ProductDetailActivity;
import com.mindworks.shoppingraga.productlisting.MainActivity;
import com.mindworks.shoppingraga.utils.LoadStaticData;

import dagger.Component;

/**
 * Created by taru on 10/16/2017.
 */

@Component(modules = {AppModule.class, ProductListModule.class, ProductdetailsModule.class,
        StaticDataInsertModule.class})
public interface AppComponent {
    void inject(MainActivity target);

    void inject(ProductDetailActivity target);

    void inject(LoadStaticData target);
}