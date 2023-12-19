package com.nhnacademy.shoppingmall.category.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Category {
    private int cateogryId;
    private String categryName;

    public Category(String categryName){
        this.categryName = categryName;
    }

    public Category(int cateogryId, String categryName){
        this.cateogryId = cateogryId;
        this.categryName = categryName;
    }

    public int getCateogryId() {
        return cateogryId;
    }

    public String getCategryName() {
        return categryName;
    }

    public void setCateogryId(int cateogryId) {
        this.cateogryId = cateogryId;
    }

    public void setCategryName(String categryName) {
        this.categryName = categryName;
    }
}