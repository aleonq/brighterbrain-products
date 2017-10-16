package com.mindworks.shoppingraga.datastore.beans;

import android.arch.persistence.room.Entity;

/**
 * Created by taru on 10/16/2017.
 */

@Entity(tableName = "stores")
public class Store {
    long id;
    String storeNmame;
    String storeLocation;
}
