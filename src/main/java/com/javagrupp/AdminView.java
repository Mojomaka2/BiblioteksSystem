package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    private Button addItemButton; 
    private Button searchItemButton;
    private Stage primaryStage;

    public AdminView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        addItemButton = new Button("Lägg till artikel");
        searchItemButton = new Button("Sök artikel");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(addItemButton, searchItemButton);

        // Lyssna på händelser från sökknappen
        searchItemButton.setOnAction(e -> openItemSearchView());

        // Set up the scene when the AdminView is created
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
    }

    public void show() {
        primaryStage.setTitle("Admin View");
        primaryStage.show();
    }

    public Button getAddItemButton() {
        return addItemButton;
    }

    // Metod för att öppna ItemSearchView
    private void openItemSearchView() {
        ItemSearchView itemSearchView = new ItemSearchView();
        itemSearchView.show();
    }
}
