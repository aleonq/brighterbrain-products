package com.mindworks.shoppingraga.utils;

import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by taru on 10/15/2017.
 */

public enum ObjectPool {
    INSTANCE;

    private HashMap<String, Object> cachedPool;

    ObjectPool() {
        cachedPool = new HashMap<>();
    }

    public Object getValue(String key) {
        return cachedPool.get(key);
    }

    public void setValue(String key, Object value) {
        cachedPool.put(key, value);
    }
}