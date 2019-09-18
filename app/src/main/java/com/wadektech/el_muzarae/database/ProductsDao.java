package com.wadektech.el_muzarae.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveProducts(List<Products> products);

    @Query("SELECT * FROM elmuzarae_db")
    List<Products> loadAllProductsFromRoom();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProducts(Products products);

    @Delete
    void deleteProductFromRoom(Products products);
}
