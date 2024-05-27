/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.ImportProduct;
import Model.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author MSI
 */
public class ImportProductDB {

    public static ArrayList<ImportProduct> getImportProducts() {
        ArrayList<ImportProduct> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = " SELECT ImportProducts.*, Suppliers.Name AS SupplierName FROM ImportProducts, Suppliers WHERE ImportProducts.SupplierId = Suppliers.Id ORDER BY ImportProducts.CreatedAt DESC";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ImportProduct item = new ImportProduct();

                item.setId(rs.getInt("Id"));
                item.setSupplierId(rs.getInt("SupplierId"));
                item.setCreatedAt(rs.getTimestamp("CreatedAt"));
                item.setTotalPayment(rs.getDouble("TotalPayment"));
                item.setSupplierName(rs.getString("SupplierName"));
                

                data.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }
    
    public static ArrayList<Product> getProductsByImportProductId(int importProductId) {
        ArrayList<Product> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT ImportProductDetails.*, Products.Name, Products.Image FROM ImportProductDetails JOIN Products ON Products.Id = ImportProductDetails.ProductId WHERE ImportProductId = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, importProductId );
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product item = new Product();

                item.setName(rs.getString("Name"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setPrice(rs.getDouble("Price"));
                item.setImage(rs.getString("Image"));
                

                data.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }


    public static int ImportProduct(int supplierId, ArrayList<Product> products) {
        BigDecimal totalPayment = BigDecimal.ZERO;
        
        for (Product product : products) {
            double totalPriceDouble = product.calculateTotalPrice();
            BigDecimal totalPriceBigDecimal = BigDecimal.valueOf(totalPriceDouble);
            totalPayment = totalPayment.add(totalPriceBigDecimal);
        }
       
         Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            
        
        try {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String insertOrderQuery = "INSERT INTO ImportProducts (SupplierId, Createdat, TotalPayment) VALUES (?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, supplierId);
            stmt.setTimestamp(2, currentTimestamp);
            stmt.setBigDecimal(3, totalPayment);

            int rowsAffected = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int insertedId = 0;
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

            if (insertedId > 0) {
               
                String insertOrderDetailsQuery = "INSERT INTO ImportProductDetails (ImportProductId, ProductId, Quantity, Price) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertOrderDetailsQuery);
                for (Product product : products) {
                    BigDecimal price = BigDecimal.valueOf(product.getPrice());
                    stmt.setInt(1, insertedId);
                    stmt.setInt(2, product.getId());
                    stmt.setInt(3, product.getQuantity());
                    stmt.setBigDecimal(4, price);
                    stmt.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction
            return insertedId;

        } catch (Exception e) {
        }

        return 0;
    }
    
    
}
