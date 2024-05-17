package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CheckoutView extends Stage {
    private CheckoutController checkoutController;

    public CheckoutView(CheckoutController checkoutController) {
        this.checkoutController = checkoutController;

        setTitle("Checkout");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Checkout date
        Label checkoutDateLabel = new Label("Checkout Date:");
        DatePicker checkoutDatePicker = new DatePicker();
        checkoutDatePicker.setValue(LocalDate.now());

        // Return date
        Label returnDateLabel = new Label("Return Date:");
        DatePicker returnDatePicker = new DatePicker();

        // Borrower ID
        Label borrowerIdLabel = new Label("Borrower ID:");
        Label borrowerIdValueLabel = new Label("1"); // Assuming borrower ID is 1 for now

        // Checkout button
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(e -> {
            LocalDate checkoutDate = checkoutDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            int borrowerId = Integer.parseInt(borrowerIdValueLabel.getText());

            boolean success = checkoutController.checkoutItems(checkoutDate, returnDate, borrowerId);
            if (success) {
                System.out.println("Checkout successful!");
            } else {
                System.out.println("Checkout failed!");
            }
        });

        GridPane.setConstraints(checkoutDateLabel, 0, 0);
        GridPane.setConstraints(checkoutDatePicker, 1, 0);
        GridPane.setConstraints(returnDateLabel, 0, 1);
        GridPane.setConstraints(returnDatePicker, 1, 1);
        GridPane.setConstraints(borrowerIdLabel, 0, 2);
        GridPane.setConstraints(borrowerIdValueLabel, 1, 2);
        GridPane.setConstraints(checkoutButton, 0, 3);

        grid.getChildren().addAll(checkoutDateLabel, checkoutDatePicker, returnDateLabel, returnDatePicker,
                borrowerIdLabel, borrowerIdValueLabel, checkoutButton);

        Scene scene = new Scene(grid, 300, 200);
        setScene(scene);
    }
}
