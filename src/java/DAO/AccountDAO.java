/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBuitls.ConnectToDB;
import DTO.AccountDTO;
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
public class AccountDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public String checkLogin(String userName, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "SELECT Role FROM Account WHERE UserName = ? AND Password = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally{
            closeConnection();
        }
        return role;
    }
    
    public  boolean  insert(AccountDTO dto) throws Exception{
        boolean check = false;
        try {
           String sql = "INSERT INTO Account(UserName, Password, FullName, Email, Phone, Address, Role, Status) VALUES(?,?,?,?,?,?,?,?)";
           con = ConnectToDB.makeConnection();
           preStm = con.prepareStatement(sql);
           preStm.setString(1, dto.getUserName());
           preStm.setString(2, dto.getPassword());
           preStm.setString(3, dto.getFullName());
           preStm.setString(4, dto.getEmail());
           preStm.setString(5, dto.getPhone());
           preStm.setString(6, dto.getAddress());
           preStm.setString(7, "user");
           preStm.setString(8, "true");
           check = preStm.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
        return check;
    }
    
    public List<AccountDTO> findUserByFullName(String search) throws Exception{
        List<AccountDTO> list = new ArrayList<>();
        String userName , fullName , email , phone , address ;
        try {
            String sql = "SELECT UserName, FullName, Email, Phone, Address FROM Account WHERE FullName LIKE ? AND Status = 1";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1,"%"+search+"%");
            rs = preStm.executeQuery();
            while (rs.next()) {                
                userName = rs.getString("UserName");
                fullName = rs.getString("FullName");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                address = rs.getString("Address");
                list.add(new AccountDTO(userName, fullName, email, phone, address));
            }
        } finally{
            closeConnection();
        }
        return list;
    }
    
    public  boolean delete(String id) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Account SET Status = ? WHERE UserName = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "false");
            preStm.setString(2, id);
            check = preStm.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
        return check;
    }
    
    public String findPhone(String username) throws Exception{
        String phone = null ;
        try {
            String sql = "SELECT Phone FROM Account WHERE UserName = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if(rs.next()){
                phone = rs.getString("Phone");
            }
        } finally{
            closeConnection();
        }
        return phone;
    }
}
