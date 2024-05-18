// ItemAddController.java
package com.javagrupp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ItemAddController {

    private TextField titleField;
    private TextField locationField;
    private TextField descriptionArea;
    private ComboBox<String> itemStatusComboBox;
    private ComboBox<String> itemTypeComboBox;
    private TextField amountField;

    public ItemAddController(TextField titleField, TextField locationField, TextField descriptionArea, ComboBox<String> itemStatusComboBox, ComboBox<String> itemTypeComboBox, TextField amountField) {
        this.titleField = titleField;
        this.locationField = locationField;
        this.descriptionArea = descriptionArea;
        this.itemStatusComboBox = itemStatusComboBox;
        this.itemTypeComboBox = itemTypeComboBox;
        this.amountField = amountField;
    }

    public void addItem(String title, String location, String description, String itemStatus, String itemType, String amountStr) {
        String sqlItem = "INSERT INTO Item (ItemID, Title, Barcode, Location, Description, ItemStatus, ItemTypeID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlStock = "INSERT INTO ItemStock (ItemID, Amount) VALUES (?, ?)";
        String sqlUpdateStock = "UPDATE ItemStock SET Amount = ? WHERE ItemID = ?";

        int amount = Integer.parseInt(amountStr);
        String barcode = BarcodeGenerator.generateBarcode();
        int itemTypeID = getItemTypeID(itemType);

        try (Connection conn = DatabaseConfig.getConnection()) {
            conn.setAutoCommit(false);

            for (int i = 0; i < amount; i++) {
                int itemID = generateUniqueItemID(); // Generera ett unikt ItemID för varje bok

                try (PreparedStatement pstmtItem = conn.prepareStatement(sqlItem);
                     PreparedStatement pstmtStock = conn.prepareStatement(sqlStock);
                     PreparedStatement pstmtUpdateStock = conn.prepareStatement(sqlUpdateStock)) {

                    pstmtItem.setInt(1, itemID);
                    pstmtItem.setString(2, title);
                    pstmtItem.setString(3, barcode); // Använd samma barcode för alla objekt av samma bok
                    pstmtItem.setString(4, location);
                    pstmtItem.setString(5, description);
                    pstmtItem.setString(6, itemStatus);
                    pstmtItem.setInt(7, itemTypeID);
                    pstmtItem.executeUpdate();

                    pstmtStock.setInt(1, itemID);
                    pstmtStock.setInt(2, 1); // Always insert 1 item for each stock entry
                    pstmtStock.executeUpdate();

                    pstmtUpdateStock.setInt(1, 1); // Always update 1 item for each stock entry
                    pstmtUpdateStock.setInt(2, itemID);
                    pstmtUpdateStock.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    conn.rollback();
                    return; // Exit the method if an exception occurs during insertion
                }
            }

            conn.commit();
            conn.setAutoCommit(true);

            System.out.println("Items have been added!");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Artiklar skapade");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Nya artiklar har skapats!");
            successAlert.showAndWait();

            titleField.clear();
            locationField.clear();
            descriptionArea.clear();
            itemStatusComboBox.getSelectionModel().clearSelection();
            itemTypeComboBox.getSelectionModel().clearSelection();
            amountField.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int generateUniqueItemID() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.hashCode());
    }

    private int getItemTypeID(String itemType) {
        switch (itemType) {
            case "Book":
                return 1;
            case "DVD":
                return 2;
            case "Standard Literature":
                return 3;
            case "Course Literature":
                return 4;
            case "Reference Literature":
                return 5;
            default:
                return 1;
        }
    }
}
