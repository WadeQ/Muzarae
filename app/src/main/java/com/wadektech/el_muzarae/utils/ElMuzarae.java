package com.wadektech.el_muzarae.utils;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class ElMuzarae extends Application {
    private static ElMuzarae elMuzarae ;

    @Override
    public void onCreate() {
        super.onCreate();

        elMuzarae = this;

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Picasso.Builder builder = new Picasso.Builder (this);
        builder.downloader (new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build ();
        built.setIndicatorsEnabled (true);
        built.setLoggingEnabled (true);
        Picasso.setSingletonInstance (built);
    }

    public static ElMuzarae getApp(){
        return elMuzarae ;
    }
}
