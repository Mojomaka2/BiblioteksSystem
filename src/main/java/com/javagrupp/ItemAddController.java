package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ItemAddController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

    private TextField titleField;
    private TextField locationField;
    private TextField descriptionArea;
    private ComboBox<String> itemStatusComboBox;
    private ComboBox<String> itemTypeComboBox;

    public ItemAddController(TextField titleField, TextField locationField, TextField descriptionArea, ComboBox<String> itemStatusComboBox, ComboBox<String> itemTypeComboBox) {
        this.titleField = titleField;
        this.locationField = locationField;
        this.descriptionArea = descriptionArea;
        this.itemStatusComboBox = itemStatusComboBox;
        this.itemTypeComboBox = itemTypeComboBox;
    }

    public void addItem(String title, String location, String description, String itemStatus, String itemType) {
        String sql = "INSERT INTO Item (ItemID, Title, Barcode, Location, Description, ItemStatus, ItemTypeID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int itemID = generateUniqueItemID();

            String barcode = BarcodeGenerator.generateBarcode();
            
            // Bestäm itemTypeID baserat på itemType
            int itemTypeID;
            switch(itemType) {
                case "Book":
                    itemTypeID = 1;
                    break;
                case "DVD":
                    itemTypeID = 2;
                    break;
                case "Standard Literature":
                    itemTypeID = 3;
                    break;
                case "Course Literature":
                    itemTypeID = 4;
                    break;
                case "Reference Literature":
                    itemTypeID = 5;
                    break;
                default:
                    // Om något oväntat värde väljs, sätt itemTypeID till 0 eller hantera det enligt behov
                    itemTypeID = 1;
                    break;
            }

            pstmt.setInt(1, itemID);
            pstmt.setString(2, title);
            pstmt.setString(3, barcode);
            pstmt.setString(4, location);
            pstmt.setString(5, description);
            pstmt.setString(6, itemStatus);
            pstmt.setInt(7, itemTypeID); // Sätt itemTypeID här

            pstmt.executeUpdate();
            System.out.println("Item has been added!");

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Artikel skapad");
            successAlert.setHeaderText(null);
            successAlert.setContentText("En ny artikel har skapats!");
            successAlert.showAndWait();

            // Rensa alla fält
            titleField.clear();
            locationField.clear();
            descriptionArea.clear();
            itemStatusComboBox.getSelectionModel().clearSelection();
            itemTypeComboBox.getSelectionModel().clearSelection();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int generateUniqueItemID() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.hashCode());
    }

}