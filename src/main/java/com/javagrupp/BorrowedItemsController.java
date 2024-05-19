package com.javagrupp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowedItemsController {
    private UserModel currentUser;
    private Stage primaryStage;
    private BorrowedItemsView view;
    private Connection connection;

    public BorrowedItemsController(UserModel currentUser, Stage primaryStage) throws SQLException {
        this.currentUser = currentUser;
        this.primaryStage = primaryStage;
        this.connection = DatabaseConfig.getConnection();

        ObservableList<BorrowedItemModel> borrowedItems = FXCollections.observableArrayList();
        if ("Admin".equals(currentUser.getRole())) {
            borrowedItems.addAll(getAllBorrowedItems());
        } else {
            borrowedItems.addAll(getBorrowedItemsForUser(currentUser.getUserID()));
        }

        this.view = new BorrowedItemsView(primaryStage, borrowedItems);
    }

    private ObservableList<BorrowedItemModel> getBorrowedItemsForUser(int userId) {
        ObservableList<BorrowedItemModel> borrowedItems = FXCollections.observableArrayList();
        String query = "SELECT i.ItemID, i.Title, c.ReturnDate, c.CheckoutStatus, u.UserName " +
               "FROM checkout c " +
               "JOIN itemstock ist ON c.StockID = ist.StockID " +
               "JOIN item i ON ist.ItemID = i.ItemID " +
               "JOIN borrower b ON c.BorrowerID = b.BorrowerID " +
               "JOIN user u ON b.UserID = u.UserID " +
               "WHERE u.UserID = ?";



        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int itemId = rs.getInt("ItemID");
                    String title = rs.getString("Title");
                    Date returnDate = rs.getDate("ReturnDate");
                    String status = rs.getString("CheckoutStatus");
                    String borrowerName = rs.getString("UserName");

                    borrowedItems.add(new BorrowedItemModel(itemId, title, returnDate.toLocalDate(), status, borrowerName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedItems;
    }

    private ObservableList<BorrowedItemModel> getAllBorrowedItems() {
        ObservableList<BorrowedItemModel> borrowedItems = FXCollections.observableArrayList();
        String query = "SELECT i.ItemID, i.Title, c.ReturnDate, c.CheckoutStatus, u.UserName " +
                       "FROM checkout c " +
                       "JOIN item i ON c.ItemID = i.ItemID " +
                       "JOIN borrower b ON c.BorrowerID = b.BorrowerID " +
                       "JOIN user u ON b.UserID = u.UserID";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int itemId = rs.getInt("ItemID");
                String title = rs.getString("Title");
                Date returnDate = rs.getDate("ReturnDate");
                String status = rs.getString("CheckoutStatus");
                String borrowerName = rs.getString("UserName");

                borrowedItems.add(new BorrowedItemModel(itemId, title, returnDate.toLocalDate(), status, borrowerName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedItems;
    }

    public void showView() {
        view.show();
    }
}
