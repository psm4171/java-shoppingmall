package com.nhnacademy.shoppingmall.product.domain;


import java.math.BigDecimal;
import java.util.List;

public class Product {
    private int productId;
    private int categoryId;
    private String modelNumber;
    private String modelName;
    private String productImg;
    private BigDecimal uniCost;
    private String description;


    public Product(int productId, int categoryId, String modelNumber, String modelName, String productImg, BigDecimal uniCost, String description){
        this.productId = productId;
        this.categoryId = categoryId;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImg = productImg;
        this.uniCost = uniCost;
        this.description = description;
    }

    public Product(){

    }

    public int getProductId() {
        return productId;
    }

    public String getImgUrl(){
        return "/resources/image" + productId + ".jpg";
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getModelName() {
        return modelName;
    }

    public String getProductImg() {
        return productImg;
    }

    public BigDecimal getUniCost() {
        return uniCost;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setUniCost(BigDecimal uniCost) {
        this.uniCost = uniCost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
