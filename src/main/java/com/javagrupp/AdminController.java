package com.javagrupp;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.stage.Stage;

public class AdminController {
    private AdminView adminView;
    private UserDAO userDAO;
    private UserModel currentUser;

    public AdminController(AdminView adminView, UserDAO userDAO, UserModel currentUser) {
        this.adminView = adminView;
        this.userDAO = userDAO;
        this.currentUser = currentUser;

        adminView.getAddItemButton().setOnAction(e -> addItem());
        adminView.getSearchItemButton().setOnAction(e -> searchItem());
        adminView.getUserManagementButton().setOnAction(e -> manageUsers());
        adminView.getBorrowedItemsButton().setOnAction(e -> {
            try {
                viewBorrowedItems();
            } catch (SQLException ex) {
                // Handle the SQLException here
                ex.printStackTrace();
            }
        });
    }

    private void addItem() {
        ItemAddView addItemView = new ItemAddView();
        addItemView.show();
    }

    private void searchItem() {
    try {
        // Skapa en anslutning till databasen
        Connection connection = DatabaseConfig.getConnection();
        
        // Skapa en instans av ItemSearchView och visa den
        ItemSearchView itemSearchView = new ItemSearchView(connection);
        itemSearchView.show();
    } catch (SQLException e) {
        e.printStackTrace();
        // Hantera fel här, t.ex. visa ett felmeddelande till användaren
    }
}


    private void manageUsers() {
        UserManagementController userManagementController = new UserManagementController(userDAO.getAllUsers(), adminView.getPrimaryStage());
        userManagementController.getView().show();
    }

    private void viewBorrowedItems() throws SQLException {
        BorrowedItemsController borrowedItemsController = new BorrowedItemsController(currentUser, adminView.getPrimaryStage());
        borrowedItemsController.showView();
    }
}
