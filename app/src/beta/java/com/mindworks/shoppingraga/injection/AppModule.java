package com.mindworks.shoppingraga.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
/**
 * Created by taru on 10/16/2017.
 */
@Module
public class AppModule {
    private Context application;

    public AppModule(Context application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }
}