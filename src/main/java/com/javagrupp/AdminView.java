package com.javagrupp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    private Button addItemButton;
    private Button searchItemButton;
    private Button userManagementButton;
    private Button borrowedItemsButton;
    private Stage primaryStage;
    private UserDAO userDAO;
    private UserModel currentUser;

    public AdminView(Stage primaryStage, UserDAO userDAO, UserModel currentUser) {
        this.primaryStage = primaryStage;
        this.userDAO = userDAO;
        this.currentUser = currentUser;

        addItemButton = new Button("Add Item");
        searchItemButton = new Button("Search Item");
        userManagementButton = new Button("User Management");
        borrowedItemsButton = new Button("Borrowed Items");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(addItemButton, searchItemButton, userManagementButton, borrowedItemsButton);

        // Listen to events from the user management button
        userManagementButton.setOnAction(e -> openUserManagementView());

        // Listen to events from the search item button
        searchItemButton.setOnAction(e -> openItemSearchView());

        // Listen to events from the borrowed items button
        borrowedItemsButton.setOnAction(e -> {
            try {
                openBorrowedItemsView();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // Set up the scene when the AdminView is created
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setTitle("Admin View");
        primaryStage.setScene(scene);
    }

    public void show() {
        primaryStage.show();
    }

    public Button getAddItemButton() {
        return addItemButton;
    }

    public Button getSearchItemButton() {
        return searchItemButton;
    }

    public Button getUserManagementButton() {
        return userManagementButton;
    }

    public Button getBorrowedItemsButton() {
        return borrowedItemsButton;
    }

    // Method to open ItemSearchView
    private void openItemSearchView() {
        try {
            Connection connection = DatabaseConfig.getConnection();
            ItemSearchView itemSearchView = new ItemSearchView(connection);
            itemSearchView.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to open UserManagementView
    private void openUserManagementView() {
        List<UserModel> userList = userDAO.getAllUsers();
        UserManagementController userManagementController = new UserManagementController(userList, primaryStage);
        UserManagementView userManagementView = new UserManagementView(userList, userManagementController);
        Stage userManagementStage = new Stage();
        userManagementStage.setScene(new Scene(userManagementView, 400, 600));
        userManagementStage.show();
    }

    // Method to open BorrowedItemsView
    private void openBorrowedItemsView() throws SQLException {
        // Implement functionality to open BorrowedItemsView
        // This could involve creating a BorrowedItemsView class and displaying it in a new stage
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
