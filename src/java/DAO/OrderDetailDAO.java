/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBuitls.ConnectToDB;
import DTO.OrderDetailDTO;
import com.sun.org.apache.xml.internal.security.encryption.Serializer;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO implements Serializable{
    private Connection con = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;
    
    public void closeConnection()throws Exception{
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
    
    public boolean insert(OrderDetailDTO dto ) throws Exception{
        boolean check = false;
        try {
            String sql = "INSERT INTO OrderDetail(FoodName, Price, Quantity, OrderID) VALUES(?,?,?,?)";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getFoodName());
            preStm.setInt(2, dto.getPrice());
            preStm.setInt(3, dto.getQuantity());
            preStm.setString(4, dto.getOderID());
            check = preStm.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
        return check;
    }
}
