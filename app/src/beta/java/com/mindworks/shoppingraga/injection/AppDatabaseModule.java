package com.mindworks.shoppingraga.injection;

import android.content.Context;

import com.mindworks.shoppingraga.datastore.sqliteroom.AppDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by taru on 10/16/2017.
 */
@Module(includes = {AppModule.class})
public class AppDatabaseModule {
    @Provides
    public AppDatabase getAppDatabase(Context context) {
        return AppDatabase.getInMemoryDatabase(context);
    }
}
