package com.mindworks.shoppingraga.datastore.sqliteroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mindworks.shoppingraga.datastore.beans.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products WHERE _id =(:productId)")
    Product getProductWithId(String productId);

    @Query("SELECT * FROM products")
    List<Product> loadAllProducts();

    @Delete
    int deleteProduct(Product product);

    @Insert
    long addProduct(Product product);

    @Update
    int updateProduct(Product product);
}