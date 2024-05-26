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
import Model.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
                product.setOriginalDecimalPrice(rs.getBigDecimal("Price"));

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
                product.setOriginalDecimalPrice(rs.getBigDecimal("Price"));
                
                data.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }

    public static boolean createProduct(Product product, String priceString, ArrayList<Category> categories) {

        BigDecimal price = new BigDecimal(priceString);

        try {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String insertProductQuery = "INSERT INTO Products(SupplierId, Name, Price, Quantity, Status, Description, Image) VALUES(?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(insertProductQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, product.getSupplierId());
            stmt.setString(2, product.getName());
            stmt.setBigDecimal(3, price);
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getStatus());
            stmt.setString(6, product.getDescription());
            stmt.setString(7, product.getImage());

            int rowsAffected = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int productId = 0;
            if (rs.next()) {
                productId = rs.getInt(1);
            }

            if (productId > 0 && categories.size() > 0) {
                // Insert các sản phẩm vào bảng categories
                String insertProductCategoryQuery = "INSERT INTO ProductCategories(ProductId, CategoryId) VALUES(?, ?)";
                stmt = conn.prepareStatement(insertProductCategoryQuery);

                for (Category cate : categories) {
                    stmt.setInt(1, productId);
                    stmt.setInt(2, cate.getId());
                    stmt.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction
            return true;

        } catch (Exception e) {
        }

        return false;
    }

    public static boolean editProduct(Product product, String priceString, ArrayList<Category> categories) {
        BigDecimal price = new BigDecimal(priceString);

        try {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Update product details
            String updateProductQuery = "UPDATE Products SET SupplierId=?, Name=?, Price=?, Quantity=?, Status=?, Description=?, Image=? WHERE Id = ?";
            PreparedStatement stmt = conn.prepareStatement(updateProductQuery);

            stmt.setInt(1, product.getSupplierId());
            stmt.setString(2, product.getName());
            stmt.setBigDecimal(3, price);
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getStatus());
            stmt.setString(6, product.getDescription());
            stmt.setString(7, product.getImage());
            stmt.setInt(8, product.getId()); // Assuming productId is available in the Product object

            int rowsAffected = stmt.executeUpdate();

            // Delete existing product-category associations
            String deleteProductCategoryQuery = "DELETE FROM ProductCategories WHERE ProductId = ?";
            stmt = conn.prepareStatement(deleteProductCategoryQuery);
            stmt.setInt(1, product.getId());
            stmt.executeUpdate();

            // Insert updated product-category associations
            if (categories.size() > 0) {
                String insertProductCategoryQuery = "INSERT INTO ProductCategories(ProductId, CategoryId) VALUES(?, ?)";
                stmt = conn.prepareStatement(insertProductCategoryQuery);

                for (Category cate : categories) {
                    stmt.setInt(1, product.getId());
                    stmt.setInt(2, cate.getId());
                    stmt.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction
            return true;

        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }

        return false;
    }

}
