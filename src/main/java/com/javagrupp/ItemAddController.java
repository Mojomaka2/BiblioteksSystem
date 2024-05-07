package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

public class ItemAddController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

    public void addItem(String title, String barcode, String location, String description, String itemStatus, String itemType, int itemTypeID) {
        String sql = "INSERT INTO Items (ItemID, Title, Barcode, Location, Description, ItemStatus, ItemType, ItemTypeID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int itemID = generateUniqueItemID();
            pstmt.setInt(1, itemID);
            pstmt.setString(2, title);
            pstmt.setString(3, barcode);
            pstmt.setString(4, location);
            pstmt.setString(5, description);
            pstmt.setString(6, itemStatus);
            pstmt.setString(7, itemType);
            pstmt.setInt(8, itemTypeID);

            pstmt.executeUpdate();
            System.out.println("Item has been added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int generateUniqueItemID() {
        UUID uuid = UUID.randomUUID();
        return uuid.hashCode();
    }
}
