/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBuitls.ConnectToDB;
import DTO.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OrderDAO implements Serializable{
    private Connection con = null;
    private PreparedStatement preStm =  null;
    private ResultSet rs = null;
    
    public  void closeConnection() throws Exception{
        if(rs != null){
            rs.close();
        }
        if(preStm != null){
            preStm.close();
        }
        if(con != null){
            con.close();
        }
    }
    
    public  boolean  checkOrderID(String orderID) throws Exception{
        boolean result = false;
        try {
            String sql = "SELECT OrderID FROM OrderConfirm WHERE OrderID = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, orderID);
            rs = preStm.executeQuery();
            if(rs.next()){
                result = true;
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean  insert(OrderDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "INSERT INTO OrderConfirm (OrderID,CustomerName,Phone,PaymentDate,TotalMoneyOfOrder) VALUES(?,?,?,?,?)";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getOrderID());
            preStm.setString(2, dto.getCustomerName());
            preStm.setString(3, dto.getPhone());
            preStm.setString(4, dto.getPaymentDate());
            preStm.setInt(5, dto.getTotalMoneyOfOrder());
            check = preStm.executeUpdate() > 0;
           
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public List<OrderDTO> listViewHistory(String dateFrom , String dateTo) throws Exception{
        List<OrderDTO> list = new ArrayList<>();
        String orderID , customerName , phone;
        int totalPrice;
        String sql = "SELECT OrderID , CustomerName ,Phone, TotalMoneyOfOrder "
                + "FROM OrderConfirm WHERE PaymentDate BETWEEN ? AND ?";
        con = ConnectToDB.makeConnection();
        preStm = con.prepareStatement(sql);
        preStm.setString(1, dateFrom);
        preStm.setString(2, dateTo);
        rs = preStm.executeQuery();
        while (rs.next()) {            
            orderID = rs.getString("OrderID");
            customerName = rs.getString("CustomerName");
            phone = rs.getString("Phone");
            totalPrice = rs.getInt("TotalMoneyOfOrder");
            list.add(new OrderDTO(orderID, customerName, phone, totalPrice));
        }
        return list;
    }
}
