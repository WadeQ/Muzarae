package com.wadektech.el_muzarae.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "elmuzarae_db")
public class Products {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id ;
    private String url ;
    private String name ;
    private String price ;

    @Ignore
    public Products(String url, String name, String price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    public Products(@NonNull int id, String url, String name, String price) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.price = price;
    }

    @Ignore
    public Products() {
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
