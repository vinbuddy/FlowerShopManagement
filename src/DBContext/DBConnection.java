/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Administrator
 */
public class DBConnection {
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_NAME = "FlowerShop";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
    

    private static final String USER = "sa";
    private static final String PASS = "123";
    private static Connection conn = null;
    
    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}