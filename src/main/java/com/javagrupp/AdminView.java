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
    private Button userManagementButton; // Lägg till en knapp för användarhantering
    private Stage primaryStage;
    private UserDAO userDAO; // Lägg till en UserDAO-instans

    public AdminView(Stage primaryStage, UserDAO userDAO) {
        this.primaryStage = primaryStage;
        this.userDAO = userDAO; // Uppdatera UserDAO-instansen

        addItemButton = new Button("Lägg till artikel");
        searchItemButton = new Button("Sök artikel");
        userManagementButton = new Button("Användarhantering"); // Skapa knappen för användarhantering

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(addItemButton, searchItemButton, userManagementButton); // Lägg till knappen för användarhantering

        // Lyssna på händelser från knappen för användarhantering
        userManagementButton.setOnAction(e -> openUserManagementView());

        // Lyssna på händelser från knappen för att söka artikel
        searchItemButton.setOnAction(e -> openItemSearchView());

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

    // Metod för att öppna ItemSearchView
private void openItemSearchView() {
    try {
        Connection connection = DatabaseConfig.getConnection(); // Hämta en anslutning från DatabaseConfig-klassen
        ItemSearchView itemSearchView = new ItemSearchView(connection); // Skapa en ny instans av ItemSearchView med den nya anslutningen
        itemSearchView.show();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Metod för att öppna UserManagementView
    private void openUserManagementView() {
        List<UserModel> userList = userDAO.getAllUsers(); // Hämta användarlistan från databasen
        UserManagementController userManagementController = new UserManagementController(userList, primaryStage);
        UserManagementView userManagementView = new UserManagementView(userList, userManagementController);
        Stage userManagementStage = new Stage();
        userManagementStage.setScene(new Scene(userManagementView, 400, 600));
        userManagementStage.show();
    }
}