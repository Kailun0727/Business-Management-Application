package com.example.bma;

import java.util.UUID;

public class Product {

    private String productName;
    private UUID productID;
    private int productQuantity;

    public Product() {

        productID = UUID.randomUUID();

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getProductID() {
        return productID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
