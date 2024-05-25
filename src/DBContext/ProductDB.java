/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Product;

public class ProductDB {

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Products LEFT JOIN DiscountProducts ON DiscountProducts.ProductId = Products.Id";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getInt("Id"));
                product.setSupplierId(rs.getInt("SupplierId"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getBigDecimal("Price").doubleValue());
                if (rs.getObject("DiscountPrice") != null) {
                    product.setDiscountPrice(rs.getBigDecimal("DiscountPrice").doubleValue());
                } else {
                    product.setDiscountPrice(0.0); 
                }
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getString("Status"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));

                data.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }
    
    public static ArrayList<Product> getProductsByCategoryIdAndName(int categoryId, String productName) {
        ArrayList<Product> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT Products.*, DiscountProducts.DiscountPrice FROM ProductCategories, Products LEFT JOIN DiscountProducts ON DiscountProducts.ProductId = Products.Id WHERE CategoryId = ? AND  Products.Id = ProductCategories.ProductId " + "AND Products.Name LIKE ?";
            
            if (productName.isEmpty()) {
                sql = "SELECT Products.*, DiscountProducts.DiscountPrice FROM ProductCategories, Products LEFT JOIN DiscountProducts ON DiscountProducts.ProductId = Products.Id WHERE CategoryId = ? AND  Products.Id = ProductCategories.ProductId ";
            }
            stmt = connection.prepareStatement(sql);
            
            
            
            if (productName.isEmpty()) { 
                stmt.setInt(1, categoryId);
            } else {
                stmt.setInt(1, categoryId);
                stmt.setString(2, "'%" + productName + "%'");
            }
            
            
            rs = stmt.executeQuery();
            
            

            while (rs.next()) {
                Product product = new Product();
                
                System.out.println(rs.getString("Name"));

                product.setId(rs.getInt("Id"));
                product.setSupplierId(rs.getInt("SupplierId"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getBigDecimal("Price").doubleValue());
                if (rs.getObject("DiscountPrice") != null) {
                    product.setDiscountPrice(rs.getBigDecimal("DiscountPrice").doubleValue());
                } else {
                    product.setDiscountPrice(0.0); 
                }
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getString("Status"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));

                data.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }

}
