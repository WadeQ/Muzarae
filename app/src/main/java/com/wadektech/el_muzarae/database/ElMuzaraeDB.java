package com.wadektech.el_muzarae.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Products.class, ProductDetails.class} , version = 1 , exportSchema = false)
public abstract class ElMuzaraeDB extends RoomDatabase {
    public static volatile ElMuzaraeDB roomInstance ;
    public static final Object LOCK = new Object() ;

    public synchronized static ElMuzaraeDB getInstance(Context context){
        if (roomInstance == null){
            synchronized (LOCK){
                if (roomInstance == null){
                    roomInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ElMuzaraeDB.class,
                            "elmuzarae_db")
                            //.allowMainThreadQueries()//for temporary testing if room is working
                            .build();
                }
            }
        }
        return roomInstance ;
    }

    public abstract ProductsDao productsDao();
    public abstract ProductDetailsDao productDetailsDao();
}
