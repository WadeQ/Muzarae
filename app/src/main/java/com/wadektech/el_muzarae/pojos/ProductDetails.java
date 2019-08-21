package com.wadektech.el_muzarae.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductDetails implements Parcelable{
    private String profileImage ;
    private String productImage ;
    private String sellingPrice ;
    private String nameOfProduct ;
    private String nameOfFarmer ;
    private String farmerState ;
    private String farmerCounty ;
    private String productQuantity ;
    private String productDescription ;
    private String farmerPhoneNumber ;

    public ProductDetails() {
    }

    public ProductDetails(String profileImage,String productImage, String sellingPrice, String nameOfProduct, String nameOfFarmer,
                          String farmerState, String farmerCounty, String productQuantity, String productDescription, String farmerPhoneNumber) {
        this.profileImage = profileImage ;
        this.productImage = productImage;
        this.sellingPrice = sellingPrice;
        this.nameOfProduct = nameOfProduct;
        this.nameOfFarmer = nameOfFarmer;
        this.farmerState = farmerState;
        this.farmerCounty = farmerCounty;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.farmerPhoneNumber = farmerPhoneNumber ;
    }

    protected ProductDetails(Parcel in) {
        profileImage = in.readString();
        productImage = in.readString();
        sellingPrice = in.readString();
        nameOfProduct = in.readString();
        nameOfFarmer = in.readString();
        farmerState = in.readString();
        farmerCounty = in.readString();
        productQuantity = in.readString();
        productDescription = in.readString();
        farmerPhoneNumber = in.readString();
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

    public String getFarmerPhoneNumber() {
        return farmerPhoneNumber;
    }

    public void setFarmerPhoneNumber(String farmerPhoneNumber) {
        this.farmerPhoneNumber = farmerPhoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getNameOfFarmer() {
        return nameOfFarmer;
    }

    public void setNameOfFarmer(String nameOfFarmer) {
        this.nameOfFarmer = nameOfFarmer;
    }

    public String getFarmerState() {
        return farmerState;
    }

    public void setFarmerState(String farmerState) {
        this.farmerState = farmerState;
    }

    public String getFarmerCounty() {
        return farmerCounty;
    }

    public void setFarmerCounty(String farmerCounty) {
        this.farmerCounty = farmerCounty;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(profileImage);
        parcel.writeString(productImage);
        parcel.writeString(sellingPrice);
        parcel.writeString(nameOfProduct);
        parcel.writeString(nameOfFarmer);
        parcel.writeString(farmerState);
        parcel.writeString(farmerCounty);
        parcel.writeString(productQuantity);
        parcel.writeString(productDescription);
        parcel.writeString(farmerPhoneNumber);
    }
}
