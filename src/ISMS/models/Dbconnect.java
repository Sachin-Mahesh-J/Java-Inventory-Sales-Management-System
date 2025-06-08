/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ISMS.models;

/**
 *
 * @author sachi
 */

import java.sql.*;
import java.util.logging.*;

public class Dbconnect {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static final Logger logger = Logger.getLogger(Dbconnect.class.getName());

    private Connection conn = null;

    public Dbconnect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Reconnecting to DB failed", e);
        }
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, "Reconnecting to DB failed", e);
        }
        return conn;
    }

    //login check
    public String logincheck(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("usertype");
                }

            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Login check failed", e);
        }
        return null;
    }
}
