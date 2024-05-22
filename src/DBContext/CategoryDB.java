/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class CategoryDB {
    public static ArrayList<Category> getCategories() {
        ArrayList<Category> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Categories";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Category item = new Category();

                item.setId(rs.getInt("Id"));
                item.setCategoryName(rs.getString("CategoryName"));
                
                data.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }
    
    public static boolean createCategory(String categoryName) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO Categories (CategoryName) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoryName);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
        }

        return false;
    } 
    
     public static boolean editCategory(int categoryId, String categoryName) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE Categories SET CategoryName = ? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoryName);
            stmt.setInt(2, categoryId);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            // Xử lý các ngoại lệ nếu cần thiết
        }
        return false;
    }
}
