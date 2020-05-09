/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class CategoryFoodDTO {
    private int categoryFoodID;
    private String categoryFoodName ;

    

    public int getCategoryFoodID() {
        return categoryFoodID;
    }

    public void setCategoryFoodID(int categoryFoodID) {
        this.categoryFoodID = categoryFoodID;
    }

    public String getCategoryFoodName() {
        return categoryFoodName;
    }

    public void setCategoryFoodName(String categoryFoodName) {
        this.categoryFoodName = categoryFoodName;
    }

    public CategoryFoodDTO(int categoryFoodID, String categoryFoodName) {
        this.categoryFoodID = categoryFoodID;
        this.categoryFoodName = categoryFoodName;
    }

    

    public CategoryFoodDTO() {
    }
    
}
