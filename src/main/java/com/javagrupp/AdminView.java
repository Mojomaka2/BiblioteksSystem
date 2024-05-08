package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    private Button addItemButton;
    private Stage primaryStage;

    public AdminView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        addItemButton = new Button("Lägg till artikel");
        addItemButton.setOnAction(e -> addItemButtonClicked());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(addItemButton);

        // Set up the scene when the AdminView is created
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
    }

    public void show() {
        primaryStage.setTitle("Admin View");
        primaryStage.show();
    }

    private void addItemButtonClicked() {
        ItemAddView addItemView = new ItemAddView(); // Create an instance of ItemAddView
        addItemView.show(); 
        // Handle what happens when the "Lägg till artikel" button is clicked
    }
}

