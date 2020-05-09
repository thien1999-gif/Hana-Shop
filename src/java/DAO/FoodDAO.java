/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBuitls.ConnectToDB;
import DTO.FoodDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FoodDAO implements Serializable {

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

    public boolean insert(FoodDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO Food(FoodName, ImageLink, Description, Price, CreateDate, CategoryFood, Status) VALUES(?,?,?,?,?,?,?)";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getFoodName());
            preStm.setString(2, dto.getImageLink());
            preStm.setString(3, dto.getDescription());
            preStm.setInt(4, dto.getPrice());
            preStm.setString(5, dto.getCreateDate());
            preStm.setInt(6, dto.getCategory());
            preStm.setString(7, "true");
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<FoodDTO> searchFoodByNameOrCategory(String search) throws Exception {
        List<FoodDTO> list = new ArrayList<>();
        String foodName, imageLink, description, createDate, categoryFoodName;
        int price;
        try {
            String sql = "SELECT Food.FoodName, Food.ImageLink, Food.Description, Food.Price,\n"
                    + "Food.CreateDate, CategoryFood.CategoryFoodName\n"
                    + "FROM Food JOIN CategoryFood ON Food.CategoryFood = CategoryFood.CategoryFoodID \n"
                    + "WHERE (FoodName LIKE ? OR CategoryFoodName LIKE ? ) AND Status = 1";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setString(2, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                foodName = rs.getString("FoodName");
                imageLink = rs.getString("ImageLink");
                description = rs.getString("Description");
                createDate = rs.getString("CreateDate");
                categoryFoodName = rs.getString("CategoryFoodName");
                price = rs.getInt("Price");
                list.add(new FoodDTO(foodName, imageLink, description, createDate, categoryFoodName, price));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean delete(String id) throws Exception {
        boolean check = false;
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateDelete = dateFormat.format(date);
            String sql = "UPDATE Food SET Status = ?, DeleteDate = ? WHERE FoodName = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "false");
            preStm.setString(2, dateDelete);
            preStm.setString(3, id);
            
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public FoodDTO findFoodByPrimarykey(String id) throws Exception {
        FoodDTO dto = null;
        String foodName;
        try {
            String sql = "SELECT FoodName FROM Food WHERE FoodName = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                foodName = rs.getString("FoodName");
                dto = new FoodDTO(foodName);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean update(FoodDTO dto) throws Exception {
        boolean check = false;
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String updateDate = dateFormat.format(date);
            String sql = "UPDATE Food SET ImageLink = ?, Description = ?, "
                    + "Price = ?, CategoryFood = ?, Status = ?, UpdateDate = ? WHERE FoodName = ?";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getImageLink());
            preStm.setString(2, dto.getDescription());
            preStm.setInt(3, dto.getPrice());
            preStm.setInt(4, dto.getCategory());
            preStm.setBoolean(5, dto.isStatus());
            preStm.setString(6, updateDate);
           preStm.setString(7, dto.getFoodName());
            check = preStm.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        } 
        finally {
            closeConnection();
        }
        return check;
    }

    public List<FoodDTO> findFoodByUser(String search) throws Exception{
        List<FoodDTO> list = new ArrayList<>();
        String foodName, imageLink, description, categoryFoodName;
        int price;
        try {
            String sql = "SELECT Food.FoodName, Food.ImageLink, Food.Description, Food.Price,\n"
                    + "CategoryFood.CategoryFoodName\n"
                    + "FROM Food JOIN CategoryFood ON Food.CategoryFood = CategoryFood.CategoryFoodID \n"
                    + "WHERE (FoodName LIKE ? OR CategoryFoodName LIKE ? ) AND Status = 1";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setString(2, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                foodName = rs.getString("FoodName");
                imageLink = rs.getString("ImageLink");
                description = rs.getString("Description");
                categoryFoodName = rs.getString("CategoryFoodName");
                price = rs.getInt("Price");
                list.add(new FoodDTO(foodName, imageLink, description, categoryFoodName, price));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
