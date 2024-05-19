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

    public List<ItemModel> searchItem(String title) {
        List<ItemModel> matchingItems = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT i.ItemID, i.Title, i.Location, i.Description, i.ItemStatus, i.Barcode, " +
                         "(SELECT SUM(s.Amount) FROM itemstock s WHERE s.ItemID = i.ItemID) AS itemStock " +
                         "FROM Item i WHERE i.Title LIKE ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + title + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String itemID = rs.getString("ItemID");
                        String foundTitle = rs.getString("Title");
                        String location = rs.getString("Location");
                        String description = rs.getString("Description");
                        String itemStatus = rs.getString("ItemStatus");
                        String barcode = rs.getString("Barcode");
                        int itemStock = rs.getInt("itemStock");

                        ItemModel item = new ItemModel(itemID, foundTitle, barcode, itemStatus, description, itemStock);
                        matchingItems.add(item);
                    }
                }
            }
        } 

        catch (SQLException e) {
            e.printStackTrace(); // or log the error message
        }

        return matchingItems;
    }

    public void deleteBook(String title) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Hitta ItemID baserat på titel
            String getItemIdSql = "SELECT ItemID FROM Item WHERE Title = ?";
            int itemId = -1;
            try (PreparedStatement stmt = conn.prepareStatement(getItemIdSql)) {
                stmt.setString(1, title);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        itemId = rs.getInt("ItemID");
                    }
                }
            }
    
            if (itemId != -1) {
                // Ta bort relaterade poster från itemstock
                String deleteItemStockSql = "DELETE FROM itemstock WHERE ItemID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(deleteItemStockSql)) {
                    stmt.setInt(1, itemId);
                    stmt.executeUpdate();
                }
    
                // Ta bort posten från item
                String deleteItemSql = "DELETE FROM Item WHERE ItemID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(deleteItemSql)) {
                    stmt.setInt(1, itemId);
                    stmt.executeUpdate();
                    System.out.println("Book '" + title + "' has been deleted from the database.");
                }
            } else {
                System.out.println("No book with the title '" + title + "' was found in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or log the error message
        }
    }

    public boolean checkoutItems(List<String> titles, int borrowerId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            for (String title : titles) {
                String sqlItem = "SELECT * FROM Item WHERE title = ?";
                try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                    stmtItem.setString(1, title);
                    try (ResultSet rsItem = stmtItem.executeQuery()) {
                        if (rsItem.next()) {
                            int itemId = rsItem.getInt("ItemID");
                            String status = rsItem.getString("ItemStatus");
                            if ("Available".equals(status)) {
                                String sqlCheckout = "INSERT INTO Checkout (CheckoutDate, ReturnDate, Fine, CheckoutStatus, BorrowerID) VALUES (CURRENT_DATE(), CURRENT_DATE() + INTERVAL 14 DAY, '0', 'Reserver', ?)";
                                try (PreparedStatement stmtCheckout = conn.prepareStatement(sqlCheckout)) {
                                    stmtCheckout.setInt(1, borrowerId);
                                    int rowsInserted = stmtCheckout.executeUpdate();
                                    if (rowsInserted > 0) {
                                        String sqlUpdateStatus = "UPDATE Item SET ItemStatus = 'Reserver' WHERE ItemID = ?";
                                        try (PreparedStatement stmtUpdateStatus = conn.prepareStatement(sqlUpdateStatus)) {
                                            stmtUpdateStatus.setInt(1, itemId);
                                            stmtUpdateStatus.executeUpdate();
                                        }
                                    }
                                }
                            } 
                            else {
                                System.out.println("Item is not available for checkout: " + title);
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isAvailable(String title) {
        ItemModel item = getItemByTitle(title);
        return item != null && "Available".equals(item.getItemStatus());
    }
    

    public ItemModel getItemByTitle(String title) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM Item INNER JOIN ItemStock ON Item.ItemID = ItemStock.ItemID WHERE title = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String itemID = rs.getString("ItemID");
                        String identifier = rs.getString("Barcode");
                        String itemStatus = rs.getString("ItemStatus");
                        String description = rs.getString("Description");
                        int itemStock = rs.getInt("Amount");
    
                        return new ItemModel(itemID, title, identifier, itemStatus, description, itemStock);
                    }
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace(); // or log the error message
        }
    
        return null; // If no item was found with the given title
    }

    public ItemModel getItemByTitle(ItemModel title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemByTitle'");
    }
}
