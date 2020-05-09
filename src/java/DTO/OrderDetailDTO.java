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
public class OrderDetailDTO implements Serializable{
    private String oderDetailID, foodName , oderID ;
    private int price, quantity;

    public OrderDetailDTO(String foodName, String oderID, int price, int quantity) {
        this.foodName = foodName;
        this.oderID = oderID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetailDTO() {
    }

    public String getOderDetailID() {
        return oderDetailID;
    }

    public void setOderDetailID(String oderDetailID) {
        this.oderDetailID = oderDetailID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getOderID() {
        return oderID;
    }

    public void setOderID(String oderID) {
        this.oderID = oderID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
