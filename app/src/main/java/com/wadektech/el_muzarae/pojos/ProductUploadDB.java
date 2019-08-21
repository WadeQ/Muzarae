package com.wadektech.el_muzarae.pojos;

public class ProductUploadDB {
    private String imageUrl ;
    private String name ;
    private String quantity ;
    private String price ;
    private String description ;

    public ProductUploadDB() {
    }

    public ProductUploadDB(String imageUrl, String name, String quantity, String price, String description) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
