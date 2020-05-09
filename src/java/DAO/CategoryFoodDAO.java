/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBuitls.ConnectToDB;
import DTO.CategoryFoodDTO;
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
public class CategoryFoodDAO implements Serializable{
    private Connection con = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;
    
    public  void closeConnection()throws Exception{
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
    public List<CategoryFoodDTO> loadFoodCategory() throws Exception{
        List<CategoryFoodDTO> list = new ArrayList<>();
        String categoryFoodName;
        int categoryFoodID;
        try {
            String sql = "SELECT CategoryFoodName, CategoryFoodID FROM CategoryFood";
            con = ConnectToDB.makeConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                categoryFoodName = rs.getString("CategoryFoodName");
                categoryFoodID = rs.getInt("CategoryFoodID");
                list.add(new CategoryFoodDTO(categoryFoodID, categoryFoodName));
            }
        } finally{
            closeConnection();
        }
        return list;
    }
}
