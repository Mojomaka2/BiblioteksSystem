package com.javagrupp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckoutDAO {
    private Connection connection;

    public CheckoutDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createCheckout(CheckoutModel checkout) {
        String sql = "INSERT INTO Checkout (CheckoutDate, ReturnDate, Fine, CheckoutStatus, BorrowerID) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(checkout.getCheckoutDate().getTime()));
            statement.setDate(2, new java.sql.Date(checkout.getReturnDate().getTime()));
            statement.setString(3, checkout.getFine());
            statement.setString(4, checkout.getCheckoutStatus());
            statement.setInt(5, checkout.getBorrowerID());

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

    public CheckoutModel getCheckoutById(int id) {
        String sql = "SELECT * FROM Checkout WHERE CheckoutID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CheckoutModel checkout = new CheckoutModel(
                        resultSet.getInt("CheckoutID"),
                        resultSet.getDate("CheckoutDate"),
                        resultSet.getDate("ReturnDate"),
                        resultSet.getString("Fine"),
                        resultSet.getString("CheckoutStatus"),
                        resultSet.getInt("BorrowerID")
                );
                return checkout;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metoder för att uppdatera och radera checkout, om det behövs

    public int getNextCheckoutID() {
        String sql = "SELECT MAX(CheckoutID) AS MaxID FROM Checkout";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int maxId = resultSet.getInt("MaxID");
                return maxId + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Return 1 if no records found in the table
    }
}
