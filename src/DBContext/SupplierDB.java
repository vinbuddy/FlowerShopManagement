/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.Product;
import Model.Supplier;
import java.math.BigDecimal;
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
public class SupplierDB {
     public static boolean createSupplier(Supplier supplier) {
         
         try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO Suppliers (Id, Name, PhoneNumber, Address) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, supplier.getId());
            stmt.setString(2, supplier.getName());
            stmt.setString(3, supplier.getPhoneNumber());
            stmt.setString(4, supplier.getAddress());
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
        }

        return false;
       
    }
     
     public static ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Suppliers";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Supplier item = new Supplier();

                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setPhoneNumber(rs.getString("PhoneNumber"));
                item.setAddress(rs.getString("Address"));


                data.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }
     
     public static boolean editSupplier(Supplier supplier) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE Suppliers SET  Name = ?, PhoneNumber = ?, Address = ? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhoneNumber());
            stmt.setString(3, supplier.getAddress());
            stmt.setInt(4, supplier.getId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            // Xử lý các ngoại lệ nếu cần thiết
        }
        return false;
    }
     
     public static boolean deleteSupplier(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM Suppliers WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu cần
        }
        return false;
    }
}
