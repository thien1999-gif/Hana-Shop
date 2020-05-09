/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class FoodDTO implements Serializable {

    private String foodName, imageLink, description, createDate, categoryFoodName, updateDateString, deleteDate;
    boolean status;
    int category, price, quantity;

    public FoodDTO(String foodName, String imageLink, String description, String createDate, int price, int category) {
        this.foodName = foodName;
        this.imageLink = imageLink;
        this.description = description;
        this.createDate = createDate;
        this.price = price;
        this.category = category;
    }

    public FoodDTO(String foodName, String imageLink, String description, String createDate, String categoryFoodName, int price) {
        this.foodName = foodName;
        this.imageLink = imageLink;
        this.description = description;
        this.createDate = createDate;
        this.categoryFoodName = categoryFoodName;
        this.price = price;
    }

    public FoodDTO(String foodName, String imageLink, String description, boolean status, int category, int price) {
        this.foodName = foodName;
        this.imageLink = imageLink;
        this.description = description;
        this.status = status;
        this.category = category;
        this.price = price;
    }

    public FoodDTO(String foodName, String imageLink, String description, String categoryFoodName, int price) {
        this.foodName = foodName;
        this.imageLink = imageLink;
        this.description = description;
        this.categoryFoodName = categoryFoodName;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    

    public String getCategoryFoodName() {
        return categoryFoodName;
    }

    public String getUpdateDateString() {
        return updateDateString;
    }

    public void setUpdateDateString(String updateDateString) {
        this.updateDateString = updateDateString;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public void setCategoryFoodName(String categoryFoodName) {
        this.categoryFoodName = categoryFoodName;
    }

    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public FoodDTO(String foodName) {
        this.foodName = foodName;
    }

    public FoodDTO() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
