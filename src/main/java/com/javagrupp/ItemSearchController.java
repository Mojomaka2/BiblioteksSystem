package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemSearchController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

     public List<String> searchItem(String title) {
        List<String> matchingTitles = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT title FROM Items WHERE title LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + title + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String foundTitle = rs.getString("title");
                        matchingTitles.add(foundTitle);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // eller logga felmeddelandet
        }

        return matchingTitles;
    }
    
    public void deleteBook(String title) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM Items WHERE title = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title);
                stmt.executeUpdate();
                System.out.println("Book '" + title + "' has been deleted from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or log the error message
        }
    }
}
