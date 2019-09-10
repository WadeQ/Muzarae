package com.wadektech.el_muzarae.viewmodels;

import androidx.lifecycle.ViewModel;

import com.wadektech.el_muzarae.pojos.Products;
import com.wadektech.el_muzarae.repository.ProductsRepository;
import com.wadektech.el_muzarae.utils.FirebaseRealtimeDatabaseQueryLiveData;

public class ProductsViewModel extends ViewModel {
    public ProductsRepository productsRepository ;
    public FirebaseRealtimeDatabaseQueryLiveData<Products> allProducts ;

    public ProductsViewModel(){
        productsRepository = ProductsRepository.getInstance();
        allProducts = productsRepository.getAllProductsFromDB();
    }

    public FirebaseRealtimeDatabaseQueryLiveData<Products> getAllProducts(){
        return allProducts ;
    }
}
