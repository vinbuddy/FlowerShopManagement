/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.Order;
import Model.Product;
import Model.ProductRevenue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class RevenueDB {
    public static ArrayList<ProductRevenue> getProductRevenue(String fromDate, String toDate) {
        ArrayList<ProductRevenue> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT " +
                     "    p.Id, " +
                     "    p.Name, " +
                     "    p.Image, " +
                     "    SUM(od.Quantity) AS QuantitySold, " +
                     "    SUM(od.Quantity * od.OrderPrice) AS TotalAmount " +
                     "FROM " +
                     "    OrderDetails od " +
                     "JOIN " +
                     "    Orders o ON od.OrderId = o.Id " +
                     "JOIN " +
                     "    Products p ON od.ProductId = p.Id " +
                     "WHERE " +
                     "    o.OrderDate BETWEEN ? AND ? " +
                     "GROUP BY " +
                     "    p.Id, p.Name, p.Image " +
                     "ORDER BY " +
                     "    QuantitySold DESC";
            
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, fromDate);
                        stmt.setString(2, toDate);

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductRevenue product = new ProductRevenue();

                product.setId(rs.getInt("Id"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImage(rs.getString("Image"));
                product.setName(rs.getString("Name"));
                product.setTotalAmount(rs.getDouble("TotalAmount"));

                data.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }
    
     public static ArrayList<Order> getOrderRevenues(String fromDate, String toDate) {
        ArrayList<Order> data = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql =  "SELECT TotalPayment, OrderDate FROM Orders WHERE OrderDate BETWEEN ? AND  ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, fromDate);
                        stmt.setString(2, toDate);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Order item = new Order();

                Date orderDate = rs.getDate("OrderDate");
                item.setOrderDate(orderDate != null ? dateFormat.format(orderDate) : null);
                item.setTotalPayment(rs.getDouble("TotalPayment"));

                data.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }
}
