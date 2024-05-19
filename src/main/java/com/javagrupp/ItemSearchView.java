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
    private ItemSearchController controller;
    private ListView<ItemModel> resultList;
    private List<String> checkoutList;
    private String currentSearchText = "";
    private List<String> selectedItems;
    private Connection connection;

    // Constructor that takes a Connection
    public ItemSearchView(Connection connection) {
        this.connection = connection;
        this.controller = new ItemSearchController();
        this.selectedItems = new ArrayList<>();
        this.checkoutList = new ArrayList<>();

        setTitle("Search Items");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        TextField searchField = new TextField();
        searchField.setPromptText("Enter item title");

        resultList = new ListView<>();
        ObservableList<ItemModel> items = FXCollections.observableArrayList();
        resultList.setItems(items);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            currentSearchText = searchField.getText();
            searchButtonClicked(currentSearchText);
        });

        Button checkoutButton = new Button("Checkout Selected Items");
        checkoutButton.setOnAction(e -> checkoutButtonClicked());

        layout.getChildren().addAll(searchField, searchButton, resultList, checkoutButton);

        Scene scene = new Scene(layout, 400, 600);
        setScene(scene);
    }

    // Constructor without a Connection
    public ItemSearchView() {
        this(null);
    }

    private void searchButtonClicked(String title) {
        List<ItemModel> matchingItems = controller.searchItem(title);
        ObservableList<ItemModel> items = FXCollections.observableArrayList(matchingItems);
        resultList.setItems(items);

        resultList.setCellFactory(param -> new ListCell<ItemModel>() {
            private final Button deleteButton = new Button("Delete");
            private final Button addButton = new Button("Add to Checkout");
            private final Button selectButton = new Button("Select");

            @Override
            protected void updateItem(ItemModel item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText("Title: " + item.getTitle() + "\nCopies Available: " + item.getItemStock());
                    HBox buttons = new HBox(5, addButton, deleteButton, selectButton);
                    setGraphic(buttons);

                    if (controller.isAvailable(item.getTitle())) {
                        selectButton.setDisable(false);
                    } else {
                        selectButton.setDisable(true);
                    }

                    selectButton.setOnAction(event -> {
                        if (selectedItems.contains(item.getTitle())) {
                            selectedItems.remove(item.getTitle());
                            selectButton.setText("Select");
                        } else {
                            selectedItems.add(item.getTitle());
                            selectButton.setText("Selected");
                        }
                    });

                    addButton.setOnAction(event -> addToCheckoutList(item.getTitle()));
                    deleteButton.setOnAction(event -> deleteButtonClicked(item));
                }
            }
        });
    }

    private void addToCheckoutList(String title) {
        if (!checkoutList.contains(title)) {
            checkoutList.add(title);
            System.out.println("Added to checkout list: " + title);
        }
    }

    private void deleteButtonClicked(ItemModel item) {
        controller.deleteBook(item.getTitle());
        // Uppdatera listan efter att en bok tagits bort
        searchButtonClicked(currentSearchText);
    }

    private void checkoutButtonClicked() {
        if (!checkoutList.isEmpty()) {
            try {
                CheckoutDAO checkoutDAO = new CheckoutDAO(connection);
                CheckoutController checkoutController = new CheckoutController(checkoutDAO);

                int borrowerId = 1; // Replace with actual borrower ID from session or context
                int maxItems = checkoutDAO.getMaxItemsForBorrower(borrowerId);
                int currentItems = checkoutDAO.getCurrentCheckedOutItemsCount(borrowerId);

                CheckoutView checkoutView = new CheckoutView(checkoutController, selectedItems, maxItems, currentItems);
                checkoutView.show();

                boolean success = controller.checkoutItems(checkoutList, borrowerId);
                if (success) {
                    System.out.println("Checkout successful!");
                    checkoutList.clear(); // Clear list after successful checkout
                } else {
                    System.out.println("Checkout failed!");
                }
            } finally {
                // Add your code here
            }
        } else {
            showAlert("No Items Selected", "Please select items to checkout.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}