package com.wadektech.el_muzarae.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductDetails implements Parcelable{
    private String profileImage ;
    private String url ;
    private String price ;
    private String nameOfProduct ;
    private String nameOfFarmer ;
    private String state ;
    private String county ;
    private String quantity ;
    private String description ;
    private String phone ;

    public ProductDetails() {
    }

    public ProductDetails(String profileImage, String url, String price, String nameOfProduct, String nameOfFarmer,
                          String state, String county, String quantity, String description, String phone) {
        this.profileImage = profileImage;
        this.url = url;
        this.price = price;
        this.nameOfProduct = nameOfProduct;
        this.nameOfFarmer = nameOfFarmer;
        this.state = state;
        this.county = county;
        this.quantity = quantity;
        this.description = description;
        this.phone = phone;
    }

    protected ProductDetails(Parcel in) {
        profileImage = in.readString();
        url = in.readString();
        price = in.readString();
        nameOfProduct = in.readString();
        nameOfFarmer = in.readString();
        state = in.readString();
        county = in.readString();
        quantity = in.readString();
        description = in.readString();
        phone = in.readString();
    }

    public static final Creator<ProductDetails> CREATOR = new Creator<ProductDetails>() {
        @Override
        public ProductDetails createFromParcel(Parcel in) {
            return new ProductDetails(in);
        }

        @Override
        public ProductDetails[] newArray(int size) {
            return new ProductDetails[size];
        }
    };

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return nameOfProduct;
    }

    public void setName(String name) {
        this.nameOfProduct = name;
    }

    public String getNameOfFarmer() {
        return nameOfFarmer;
    }

    public void setNameOfFarmer(String nameOfFarmer) {
        this.nameOfFarmer = nameOfFarmer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(profileImage);
        parcel.writeString(url);
        parcel.writeString(price);
        parcel.writeString(nameOfProduct);
        parcel.writeString(nameOfFarmer);
        parcel.writeString(state);
        parcel.writeString(county);
        parcel.writeString(quantity);
        parcel.writeString(description);
        parcel.writeString(phone);
    }
}
