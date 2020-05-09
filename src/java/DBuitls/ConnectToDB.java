/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBuitls;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class ConnectToDB implements Serializable {

    public static Connection makeConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HanaShop";
            con = DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
