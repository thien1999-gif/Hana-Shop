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
public class FoodDTOErrorObj implements Serializable{
    private String  foodNameError , imageLinkError , descriptionError , createDateError, priceError, statusError, deleteDateError , updateDateError ;
    String categoryError;

    public String getDeleteDateError() {
        return deleteDateError;
    }

    public void setDeleteDateError(String deleteDateError) {
        this.deleteDateError = deleteDateError;
    }

    public String getUpdateDateError() {
        return updateDateError;
    }

    public void setUpdateDateError(String updateDateError) {
        this.updateDateError = updateDateError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getFoodNameError() {
        return foodNameError;
    }

    public void setFoodNameError(String foodNameError) {
        this.foodNameError = foodNameError;
    }

    public String getImageLinkError() {
        return imageLinkError;
    }

    public void setImageLinkError(String imageLinkError) {
        this.imageLinkError = imageLinkError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getCreateDateError() {
        return createDateError;
    }

    public void setCreateDateError(String createDateError) {
        this.createDateError = createDateError;
    }

    public String getCategoryError() {
        return categoryError;
    }

    public void setCategoryError(String categoryError) {
        this.categoryError = categoryError;
    }

    
}
