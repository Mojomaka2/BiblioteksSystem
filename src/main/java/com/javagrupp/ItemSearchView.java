package com.javagrupp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemSearchView extends Stage {
    private List<String> selectedItems;
    private ItemSearchController itemSearchController;
    private Connection connection;

    public ItemSearchView(Connection connection) {
        this.connection = connection;
        this.itemSearchController = new ItemSearchController();
        this.selectedItems = new ArrayList<>();

        setTitle("Search Items");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        TextField searchField = new TextField();
        searchField.setPromptText("Enter item title");

        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        listView.setItems(items);
        listView.setCellFactory(param -> new ListCell<String>() {
            private final Button selectButton = new Button("Select");

            @Override
            protected void updateItem(String title, boolean empty) {
                super.updateItem(title, empty);
                if (empty || title == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(title);
                    HBox buttons = new HBox(5, selectButton);
                    setGraphic(buttons);

                    // Check if item is available and enable/disable select button accordingly
                    if (itemSearchController.isAvailable(title)) {
                        selectButton.setDisable(false);
                    } else {
                        selectButton.setDisable(true);
                    }

                    selectButton.setOnAction(event -> {
                        if (selectedItems.contains(title)) {
                            selectedItems.remove(title);
                            selectButton.setText("Select");
                        } else {
                            selectedItems.add(title);
                            selectButton.setText("Selected");
                        }
                    });
                }
            }
        });

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            String query = searchField.getText();
            items.clear();
            items.addAll(itemSearchController.searchItem(query));
        });

        Button checkoutButton = new Button("Checkout Selected Items");
        checkoutButton.setOnAction(e -> {
            if (!selectedItems.isEmpty()) {
                try {
                    CheckoutDAO checkoutDAO = new CheckoutDAO(connection);
                    CheckoutController checkoutController = new CheckoutController(checkoutDAO);

                    int borrowerId = 1; // Replace with actual borrower ID from session or context
                    int maxItems = checkoutDAO.getMaxItemsForBorrower(borrowerId);
                    int currentItems = checkoutDAO.getCurrentCheckedOutItemsCount(borrowerId);

                    CheckoutView checkoutView = new CheckoutView(checkoutController, selectedItems, maxItems, currentItems);
                    checkoutView.show();
                } finally {
                    // Add your code here
                }
            } else {
                showAlert("No Items Selected", "Please select items to checkout.");
            }
        });

        layout.getChildren().addAll(searchField, searchButton, listView, checkoutButton);

        Scene scene = new Scene(layout, 400, 300);
        setScene(scene);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
