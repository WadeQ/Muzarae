package com.wadektech.el_muzarae.utils;

import android.app.Application;

public class ElMuzarae extends Application {
    private static ElMuzarae elMuzarae ;

    @Override
    public void onCreate() {
        super.onCreate();

        elMuzarae = this;
    }

    public static ElMuzarae getApp(){
        return elMuzarae ;
    }
}
