package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItemAddView extends Stage {
    private ItemAddController controller;

    public ItemAddView() {
        System.err.println("ItemAddView constructor called"); // Debugging statement
        controller = new ItemAddController(); // Create an instance of ItemAddController

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Barcode");
        TextField locationField = new TextField();
        locationField.setPromptText("Location");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        ComboBox<String> itemStatusField = new ComboBox<>();
        itemStatusField.getItems().addAll("Available", "Reserved", "Checked Out", "Overdue");
        TextField itemTypeIDField = new TextField();
        itemTypeIDField.setPromptText("Item Type ID");
        ComboBox<String> itemTypeField = new ComboBox<>();
        itemTypeField.getItems().addAll("Book", "DVD");
        Button button = new Button("Add Item");

        button.setOnAction(e -> {
            String title = titleField.getText();
            String barcode = barcodeField.getText();
            String location = locationField.getText();
            String description = descriptionField.getText();
            String itemStatus = itemStatusField.getValue();
            String itemType = itemTypeField.getValue();
            int itemTypeID = Integer.parseInt(itemTypeIDField.getText());

            controller.addItem(title, barcode, location, description, itemStatus, itemType, itemTypeID);
        });

        VBox vbox = new VBox(titleField, barcodeField, locationField, descriptionField, itemStatusField, itemTypeField, itemTypeIDField, button);
        Scene scene = new Scene(vbox, 600, 300);

        this.setScene(scene);
        this.setTitle("Add Item");
    }
}
