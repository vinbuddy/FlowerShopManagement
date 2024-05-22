/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.Order;
import Model.OrderDetail;
import Model.Product;
import Model.ShippingOrder;
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

            String insertOrderQuery = "INSERT INTO Orders (TotalPayment, OrderDate, ReceiveDate, Status) VALUES (?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setBigDecimal(1, totalPayment);
            stmt.setDate(2, Date.valueOf(orderDate));
            stmt.setDate(3, Date.valueOf(orderDate));
            stmt.setString(4, "Hoàn thành");

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

    public static ArrayList<Order> getOrders() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Order> list = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Orders";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("Id"));
                order.setUserId(rs.getInt("UserId"));
                order.setStatus(rs.getString("Status"));
                order.setNote(rs.getString("Note"));

                BigDecimal totalPayment = rs.getBigDecimal("TotalPayment");
                order.setTotalPayment(totalPayment != null ? totalPayment.doubleValue() : 0.0);

                Date orderDate = rs.getDate("OrderDate");
                order.setOrderDate(orderDate != null ? dateFormat.format(orderDate) : null);

                Date receiveDate = rs.getDate("ReceiveDate");
                order.setReceiveDate(receiveDate != null ? dateFormat.format(receiveDate) : null);

                order.setReceiveTime(rs.getString("ReceiveTime"));

                BigDecimal shippingCost = rs.getBigDecimal("ShippingCost");
                order.setShippingCost(shippingCost != null ? shippingCost.doubleValue() : 0.0);

                list.add(order);
            }

        } catch (Exception e) {
            throw new Error(e.getMessage());
        }

        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetails(int orderId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<OrderDetail> list = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT OrderDetails.*, Products.Name AS ProductName, Products.Image AS ProductImage, OrderDetails.OrderPrice * OrderDetails.Quantity AS TotalOrderPrice FROM OrderDetails FULL OUTER JOIN Products ON Products.Id = OrderDetails.ProductId WHERE OrderDetails.OrderId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);

            rs = stmt.executeQuery();


            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                
                orderDetail.setId(rs.getInt("Id"));
                orderDetail.setOrderId(rs.getInt("OrderId"));
                orderDetail.setProductId(rs.getInt("ProductId"));
                orderDetail.setQuantity(rs.getInt("Quantity"));
                orderDetail.setProductName(rs.getString("ProductName"));
                orderDetail.setProductImage(rs.getString("ProductImage"));
                orderDetail.setTotalOrderPrice(rs.getBigDecimal("TotalOrderPrice").doubleValue());
                orderDetail.setOrderPrice(rs.getBigDecimal("OrderPrice").doubleValue());

                list.add(orderDetail);
            }

        } catch (Exception e) {
            throw new Error("Lỗi khi get dữ liệu");
        }

        return list;
    }

    public static ShippingOrder getShippingOrder(int orderId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ShippingOrder shippingOrder = new ShippingOrder();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM ShippingOrders WHERE OrderId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                shippingOrder.setId(rs.getInt("Id"));
                shippingOrder.setOrderId(rs.getInt("OrderId"));
                shippingOrder.setPhoneNumber(rs.getString("PhoneNumber"));
                shippingOrder.setFullName(rs.getString("FullName"));
                shippingOrder.setCity(rs.getString("City"));
                shippingOrder.setDistrict(rs.getString("District"));
                shippingOrder.setWard(rs.getString("Ward"));
                shippingOrder.setAddress(rs.getString("Address"));

            }

        } catch (Exception e) {
            throw new Error("Lỗi khi get dữ liệu");
        }

        return shippingOrder;
    }
    
    public static boolean updateOrderStatus(int orderId, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE Orders SET Status = ? WHERE Id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            int rowsAffected = stmt.executeUpdate();
            

            return rowsAffected > 0;
        } catch (Exception e) {
          
        }
        return false;
    }
}
