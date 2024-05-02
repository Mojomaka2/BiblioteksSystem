package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField titleField = new TextField();
        titleField.setPromptText("Titel");
        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Streckkod");
        TextField locationField = new TextField();
        locationField.setPromptText("Plats");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Beskrivning");
        ComboBox<String> itemStatusField = new ComboBox<>();
        itemStatusField.getItems().addAll("Available", "Reserver", "Checked Out", "Overdue");
        TextField itemTypeIDField = new TextField();
        itemTypeIDField.setPromptText("Artikeltyp-ID");
        Button button = new Button("Lägg till bok");

        button.setOnAction(e -> {
            String title = titleField.getText();
            String barcode = barcodeField.getText();
            String location = locationField.getText();
            String description = descriptionField.getText();
            String itemStatus = itemStatusField.getValue();
            int itemTypeID = Integer.parseInt(itemTypeIDField.getText());

            addBook(title, barcode, location, description, itemStatus, itemTypeID);
        });

        VBox vbox = new VBox(titleField, barcodeField, locationField, descriptionField, itemStatusField, itemTypeIDField, button);
        Scene scene = new Scene(vbox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBook(String title, String barcode, String location, String description, String itemStatus, int itemTypeID) {
        String sql = "INSERT INTO Books (ItemID, Title, BarCode, Location, Description, ItemStatus, ItemTypeID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int itemID = generateUniqueItemID();

            pstmt.setInt(1, itemID);
            pstmt.setString(2, title);
            pstmt.setString(3, barcode);
            pstmt.setString(4, location);
            pstmt.setString(5, description);
            pstmt.setString(6, itemStatus);
            pstmt.setInt(7, itemTypeID);

            pstmt.executeUpdate();
            System.out.println("Boken har lagts till!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int generateUniqueItemID() {
        UUID uuid = UUID.randomUUID();
        return uuid.hashCode();
    }
}
