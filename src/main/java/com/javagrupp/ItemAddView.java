package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItemAddView extends Stage {
    private ItemAddController controller;

    public ItemAddView() {
        System.err.println("ItemAddView constructor called"); // Debugging statement

        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField locationField = new TextField();
        locationField.setPromptText("Location");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        ComboBox<String> itemStatusField = new ComboBox<>();
        itemStatusField.setPromptText("Status");
        itemStatusField.getItems().addAll("Available", "Reserver", "Checked Out", "Overdue"); //"Reserved" måste vara felstavat för att skiten ska funka XD
        
        ComboBox<String> itemTypeIDField = new ComboBox<>();
        itemTypeIDField.setPromptText("Item Type");
        itemTypeIDField.getItems().addAll("Book", "DVD", "Standard Literature", "Course Literature", "Reference Literature");
        Label itemTypeLabel = new Label("Item Type:");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button button = new Button("Add Item");

        controller = new ItemAddController(titleField, locationField, descriptionField, itemStatusField, itemTypeIDField, amountField);


        button.setOnAction(e -> {
            String title = titleField.getText();
            String location = locationField.getText();
            String description = descriptionField.getText();
            String itemStatus = itemStatusField.getValue();
            String itemType = itemTypeIDField.getValue();
            try {
                String amount = amountField.getText();
                controller.addItem(title, location, description, itemStatus, itemType, amount);
            } 
            catch (NumberFormatException ex) {
                controller.showAlert(Alert.AlertType.WARNING, "Invalid Input", "Amount must be a valid number.");
            }
        });

        VBox vbox = new VBox(titleField, locationField, descriptionField, itemStatusField, itemTypeIDField, amountField, button);
        Scene scene = new Scene(vbox, 600, 350);

        this.setScene(scene);
        this.setTitle("Add Item");
    }
}

