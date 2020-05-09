/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class CartDTO implements Serializable{
    private String customerName ;
    private HashMap<String,FoodDTO> shoppingCart ;
    
    public CartDTO(){
        this.customerName = "GUEST";
        this.shoppingCart = new HashMap<>();
    }
    
    public CartDTO(String customerName){
        this.customerName = this.customerName;
        this.shoppingCart = new HashMap<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, FoodDTO> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<String, FoodDTO> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public void addToCart(FoodDTO dto) throws Exception {
        if (this.shoppingCart.containsKey(dto.getFoodName())) {
            int newQuantity = this.shoppingCart.get(dto.getFoodName()).getQuantity() + dto.getQuantity();
            dto.setQuantity(newQuantity);
        }
        this.shoppingCart.put(dto.getFoodName(), dto);
    }
    
     public int getTotal() throws Exception{
        int result = 0;
        
        for (FoodDTO value : this.shoppingCart.values()) {
            result += (value.getQuantity() * value.getPrice());
        }
        return result;
    }
    
    public void removeCart(String id) throws Exception{
        if (this.shoppingCart.containsKey(id)){
            this.shoppingCart.remove(id);
        }
    }
    
    public void updateCart(String id, int quantity) throws Exception{
        if (this.shoppingCart.containsKey(id)){
            this.shoppingCart.get(id).setQuantity(quantity);
        }
    }
}
