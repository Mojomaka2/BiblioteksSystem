package com.javagrupp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemStockDAO {
    private static final String INSERT_STOCK_SQL = "INSERT INTO ItemStock (Amount, ItemID) VALUES (?, ?)";
    private static final String UPDATE_STOCK_SQL = "UPDATE ItemStock SET Amount = ? WHERE ItemID = ?";
    private static final String SELECT_STOCK_SQL = "SELECT * FROM ItemStock JOIN Item ON ItemStock.ItemID = Item.ItemID WHERE Barcode = ?";
    private static final String DELETE_STOCK_SQL = "DELETE FROM ItemStock WHERE ItemID = ?";

    private Connection connection;

    public ItemStockDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addItemStock(int amount, int itemID) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_STOCK_SQL)) {
            stmt.setInt(1, amount);
            stmt.setInt(2, itemID);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateItemStock(int amount, int itemID) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_STOCK_SQL)) {
            stmt.setInt(1, amount);
            stmt.setInt(2, itemID);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ItemStock getItemStock(String barcode) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_STOCK_SQL)) {
            stmt.setString(1, barcode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int stockID = rs.getInt("StockID");
                    int amount = rs.getInt("Amount");
                    int itemID = rs.getInt("ItemID");
                    return new ItemStock(stockID, amount, itemID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteItemStock(int itemID) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_STOCK_SQL)) {
            stmt.setInt(1, itemID);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
