package com.mindworks.shoppingraga.datastore.beans;

import android.arch.persistence.room.Entity;

/**
 * Created by taru on 10/16/2017.
 */

@Entity(tableName = "product_store")
public class ProductStoreMapping {
    long productId;
    long storeId;
}