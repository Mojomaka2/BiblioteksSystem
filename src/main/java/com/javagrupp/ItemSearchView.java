package com.javagrupp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchView extends Stage {
    private ItemSearchController controller;
    private ListView<String> resultList;
    private List<String> checkoutList;

    public ItemSearchView() {
        controller = new ItemSearchController();
        checkoutList = new ArrayList<>();

        TextField searchField = new TextField();
        searchField.setPromptText("Sök objekt");
        Button searchButton = new Button("Sök");
        Button checkoutButton = new Button("Checkout");

        resultList = new ListView<>();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        HBox topLayout = new HBox(10);
        topLayout.getChildren().addAll(searchField, searchButton, checkoutButton);
        layout.getChildren().addAll(topLayout, resultList);

        searchButton.setOnAction(e -> searchButtonClicked(searchField.getText()));
        checkoutButton.setOnAction(e -> checkoutButtonClicked());

        Scene scene = new Scene(layout, 400, 600);
        setScene(scene);
        setTitle("Sök objekt");
    }

    private void searchButtonClicked(String title) {
        List<String> matchingTitles = controller.searchItem(title);
        ObservableList<String> items = FXCollections.observableArrayList(matchingTitles);
        resultList.setItems(items);

        resultList.setCellFactory(param -> new ListCell<String>() {
            private final Button deleteButton = new Button("Delete");
            private final Button addButton = new Button("Lägg till till Checkout");

            @Override
            protected void updateItem(String title, boolean empty) {
                super.updateItem(title, empty);

                if (empty || title == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(title);
                    HBox buttons = new HBox(5, addButton, deleteButton);
                    setGraphic(buttons);
                    addButton.setOnAction(event -> addToCheckoutList(title));
                    deleteButton.setOnAction(event -> deleteButtonClicked(title));
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

    private void deleteButtonClicked(String title) {
        controller.deleteBook(title);
    }

    private void checkoutButtonClicked() {
        // Här borde vi passera borrowerId och staffId, anta att de är 1 för nu
        int borrowerId = 1; // Detta borde vara dynamiskt i en riktig applikation
        int staffId = 1;    // Detta borde vara dynamiskt i en riktig applikation
        boolean success = controller.checkoutItems(checkoutList, borrowerId, staffId);
        if (success) {
            System.out.println("Checkout successful!");
            checkoutList.clear(); // Rensa listan efter framgångsrik utcheckning
        } else {
            System.out.println("Checkout failed!");
        }
    }
}
