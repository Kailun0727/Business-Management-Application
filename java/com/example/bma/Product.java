package com.example.bma;

import java.util.UUID;

public class Product {

    private String productName;
    private int productID;
    private int productQuantity;
    private int productPrice;

    public Product() {


    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductID() {
        return productID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
