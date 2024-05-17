package com.javagrupp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class CheckoutDAO {
    private Connection connection;

    public CheckoutDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createCheckout(CheckoutModel checkout) {
        String sql = "INSERT INTO Checkout (CheckoutDate, ReturnDate, Fine, CheckoutStatus, BorrowerID, StaffID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(checkout.getCheckoutDate().getTime()));
            statement.setDate(2, new java.sql.Date(checkout.getReturnDate().getTime()));
            statement.setString(3, checkout.getFine());
            statement.setString(4, checkout.getCheckoutStatus());
            statement.setInt(5, checkout.getBorrowerID());
            statement.setInt(6, checkout.getStaffID());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateItemStatus(int itemId, String status) {
        String sql = "UPDATE Item SET ItemStatus = ? WHERE ItemID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, itemId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
