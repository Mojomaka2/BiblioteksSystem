package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutView extends Stage {
    private CheckoutController checkoutController;
    private List<String> selectedItems;
    private int maxItems;
    private int currentItems;
    private ItemSearchController itemSearchController;

    public CheckoutView(CheckoutController checkoutController, List<String> selectedItems, int maxItems, int currentItems) {
        this.checkoutController = checkoutController;
        this.selectedItems = selectedItems;
        this.maxItems = maxItems;
        this.currentItems = currentItems;
        this.itemSearchController = new ItemSearchController();

        setTitle("Checkout Items");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Item titles
        Label titleLabel = new Label("Selected Items:");
        VBox itemsBox = new VBox();
        for (int i = 0; i < selectedItems.size(); i++) {
            String item = selectedItems.get(i);
            Label itemLabel = new Label((i + 1) + ". " + item + " - Select Return Date for " + item);
            itemsBox.getChildren().add(itemLabel);
        }

        // Remaining items you can checkout
        Label remainingLabel = new Label("Remaining Items you can checkout:");
        Label remainingCountLabel = new Label(String.valueOf(maxItems - currentItems));

        // Checkout date
        Label checkoutDateLabel = new Label("Checkout Date:");
        DatePicker checkoutDatePicker = new DatePicker();
        checkoutDatePicker.setValue(LocalDate.now());

        // Pick ReturnDate
        Label returnDateLabel = new Label("Pick Return Date:");
        DatePicker returnDatePicker = new DatePicker();

        // Checkout button
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(e -> {
            LocalDate checkoutDate = checkoutDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            int borrowerId = 1; // Placeholder for borrower ID

            Map<String, LocalDate> itemsWithReturnDate = new HashMap<>();
            for (String itemTitle : selectedItems) {
                ItemModel item = itemSearchController.getItemByTitle(itemTitle);
                if (item != null && "Available".equals(item.getItemStatus())) {
                    itemsWithReturnDate.put(itemTitle, returnDate);
                } else {
                    System.out.println("Item is not available for checkout: " + itemTitle);
                    return; // Exit the loop if an item is not available
                }
            }

            boolean success = checkoutController.checkoutItems(checkoutDate, itemsWithReturnDate, borrowerId);
            if (success) {
                System.out.println("Checkout successful!");
            } else {
                System.out.println("Checkout failed!");
            }
        });

        GridPane.setConstraints(titleLabel, 0, 0);
        GridPane.setConstraints(itemsBox, 1, 0);
        GridPane.setConstraints(remainingLabel, 0, 1);
        GridPane.setConstraints(remainingCountLabel, 1, 1);
        GridPane.setConstraints(checkoutDateLabel, 0, 2);
        GridPane.setConstraints(checkoutDatePicker, 1, 2);
        GridPane.setConstraints(returnDateLabel, 0, 3);
        GridPane.setConstraints(returnDatePicker, 1, 3);
        GridPane.setConstraints(checkoutButton, 0, 4);

        grid.getChildren().addAll(titleLabel, itemsBox, remainingLabel, remainingCountLabel,
                checkoutDateLabel, checkoutDatePicker, returnDateLabel, returnDatePicker, checkoutButton);

        Scene scene = new Scene(grid, 400, 250);
        setScene(scene);
    }
}
