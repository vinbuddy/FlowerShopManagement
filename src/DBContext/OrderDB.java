/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderDB {

    public static boolean createOrder(ArrayList<Product> selectedProducts) {
        BigDecimal totalPayment = BigDecimal.ZERO;
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = dateFormat.format(currentDate);

        for (Product product : selectedProducts) {
            double totalPriceDouble = product.calculateTotalPrice();
            BigDecimal totalPriceBigDecimal = BigDecimal.valueOf(totalPriceDouble);
            totalPayment = totalPayment.add(totalPriceBigDecimal);
        }

        try {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String insertOrderQuery = "INSERT INTO Orders (TotalPayment, OrderDate, ReceiveDate) VALUES (?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setBigDecimal(1, totalPayment);
            stmt.setDate(2, Date.valueOf(orderDate));
            stmt.setDate(3, Date.valueOf(orderDate));
            int rowsAffected = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            if (orderId > 0) {
                // Insert các sản phẩm vào bảng OrderDetails
                String insertOrderDetailsQuery = "INSERT INTO OrderDetails (OrderId, ProductId, Quantity, OrderPrice) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertOrderDetailsQuery);
                for (Product product : selectedProducts) {
                    BigDecimal orderPrice = BigDecimal.valueOf(product.getPrice());
                    stmt.setInt(1, orderId);
                    stmt.setInt(2, product.getId());
                    stmt.setInt(3, product.getQuantity());
                    stmt.setBigDecimal(4, orderPrice);
                    stmt.executeUpdate();
                }
            }
            
            conn.commit(); // Commit transaction
            return true;

        } catch (Exception e) {
            
        }

        return false;
    }
}
