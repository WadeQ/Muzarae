package com.wadektech.el_muzarae.viewmodels;

import androidx.lifecycle.ViewModel;

import com.wadektech.el_muzarae.database.ElMuzaraeDB;
import com.wadektech.el_muzarae.database.Products;
import com.wadektech.el_muzarae.database.ProductsDao;
import com.wadektech.el_muzarae.repository.ProductsRepository;
import com.wadektech.el_muzarae.utils.FirebaseRealtimeDatabaseQueryLiveData;

public class ProductsViewModel extends ViewModel {
    public ProductsRepository productsRepository ;
    public FirebaseRealtimeDatabaseQueryLiveData<Products> allProducts ;
    public ElMuzaraeDB elMuzaraeDB ;
    public ProductsDao productsDao ;

    public ProductsViewModel(){
        productsRepository = ProductsRepository.getInstance();
        allProducts = productsRepository.getAllProductsFromDB();
        //elMuzaraeDB = ElMuzaraeDB.getInstance(context);
    }

    public FirebaseRealtimeDatabaseQueryLiveData<Products> getAllProducts(){
        return allProducts ;
    }
}
