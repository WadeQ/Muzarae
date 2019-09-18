package com.wadektech.el_muzarae.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDetailsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveProducts(List<ProductDetails> products);

    @Query("SELECT * FROM elmuzarae_db")
    List<ProductDetails> loadAllProductsFromRoom();
}
