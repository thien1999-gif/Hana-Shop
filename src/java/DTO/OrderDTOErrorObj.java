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
public class OrderDTOErrorObj implements Serializable{
    private String dateFromErrorObj , dateToErrorObj;

    public OrderDTOErrorObj() {
    }

    public String getDateFromErrorObj() {
        return dateFromErrorObj;
    }

    public void setDateFromErrorObj(String dateFromErrorObj) {
        this.dateFromErrorObj = dateFromErrorObj;
    }

    public String getDateToErrorObj() {
        return dateToErrorObj;
    }

    public void setDateToErrorObj(String dateToErrorObj) {
        this.dateToErrorObj = dateToErrorObj;
    }
    
}
