package com.wadektech.el_muzarae.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wadektech.el_muzarae.database.Products;
import com.wadektech.el_muzarae.utils.FirebaseRealtimeDatabaseQueryLiveData;

public class ProductsRepository {
    public static volatile ProductsRepository pInstance ;
    public static final Object Lock = new Object();
    private static final String TAG = "ProductsRepository";

    public synchronized static ProductsRepository getInstance(){
        if (pInstance == null){
            synchronized (Lock){
                if (pInstance == null){
                    pInstance = new ProductsRepository();
                }
            }
        }
        return pInstance ;
    }

    public FirebaseRealtimeDatabaseQueryLiveData<Products> getAllProductsFromDB(){
        final DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("Products");
        return new FirebaseRealtimeDatabaseQueryLiveData<>(Products.class, dRef);
    }
}
