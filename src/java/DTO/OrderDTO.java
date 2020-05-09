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
public class OrderDTO implements Serializable{
    private String orderID , customerName , phone , paymentDate ;
    private int totalMoneyOfOrder ;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String customerName, String phone, int totalMoneyOfOrder) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.phone = phone;
        this.totalMoneyOfOrder = totalMoneyOfOrder;
    }

    public OrderDTO(String orderID, String customerName, String phone, String paymentDate, int totalMoneyOfOrder) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.phone = phone;
        this.paymentDate = paymentDate;
        this.totalMoneyOfOrder = totalMoneyOfOrder;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getTotalMoneyOfOrder() {
        return totalMoneyOfOrder;
    }

    public void setTotalMoneyOfOrder(int totalMoneyOfOrder) {
        this.totalMoneyOfOrder = totalMoneyOfOrder;
    }

    @Override
    public String toString() {
        return customerName +"," + orderID + ","  +paymentDate + "," +phone + ","+ totalMoneyOfOrder;
    }

    
    
}
