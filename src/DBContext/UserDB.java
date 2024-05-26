/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBContext;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class UserDB {

    public static boolean isAdminRole(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isAdmin = false;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT r.RoleName "
                    + "FROM Users u "
                    + "JOIN UserRoles ur ON u.Id = ur.UserId "
                    + "JOIN Roles r ON ur.RoleId = r.Id "
                    + "WHERE u.Id = ? AND r.RoleName = 'Admin'";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                isAdmin = true;
            }

        } catch (Exception e) {
            throw new Error(e.getMessage());
        }

        return isAdmin;
    }

    public static User login(String email, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = new User();

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("Id"));
                user.setUserName(rs.getString("UserName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setAvatar(rs.getString("Avatar"));
            }

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<User> getCustomers() {
        ArrayList<User> data = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM Users";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                boolean isAdmin = isAdminRole(rs.getInt("Id"));
                if (!isAdmin) {

                    User item = new User();

                    item.setId(rs.getInt("Id"));
                    item.setUserName(rs.getString("UserName"));
                    item.setEmail(rs.getString("Email"));
                    item.setAvatar(rs.getString("Avatar"));

                    data.add(item);
                }

            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return data;
    }

}
