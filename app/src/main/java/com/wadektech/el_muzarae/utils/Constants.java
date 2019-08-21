package com.wadektech.el_muzarae.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {
    public static final String farmerPhoneNumber = "farmerNumber";
    public static final String productDescription = "productDescription";
    public static final String farmerCounty = "farmerCounty";
    public static final String farmerState = "farmerState";
    public static final String nameOfFarmer = "nameOfFarmer";
    public static final String nameOfProduct = "nameOfProduct";
    public static final String productQuantity = "productQuantity";
    public static final String sellingPrice = "sellingPrice";
    public static final String productImage = "productImage";
    public static final String profileImage = "profileImage";
    public static final String PREF = "myPref";

    public static String getFarmerPhoneNumber(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.farmerPhoneNumber, "") ;
    }

    public static String getProductDescription(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.productDescription, "") ;
    }


    public static String getFarmerCounty(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.farmerCounty, "") ;
    }

    public static String getFarmerState(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.farmerState, "") ;
    }

    public static String getNameOfFarmer(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.nameOfFarmer, "") ;
    }

    public static String getNameOfProduct(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.nameOfProduct, "") ;
    }

    public static String getProductImage(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.productImage, "") ;
    }

    public static String getProfileImage(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.profileImage, "") ;
    }

    public static String getProductQuantity(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.productQuantity, "") ;
    }
    public static String getSellingPrice(){
        SharedPreferences sps = ElMuzarae
                .getApp()
                .getApplicationContext()
                .getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        return sps.getString(Constants.sellingPrice, "") ;
    }
}