package com.javagrupp;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class CheckoutController {
    private CheckoutDAO checkoutDAO;
    private ItemSearchController itemSearchController;

    public CheckoutController(Connection connection) {
        this.checkoutDAO = new CheckoutDAO(connection);
        this.itemSearchController = new ItemSearchController(); // Assuming this controller can get item details
    }

    public boolean checkoutItems(LocalDate checkoutDate, LocalDate returnDate, int borrowerId) {
        // Convert LocalDate to Date
        Date checkoutDateAsDate = Date.valueOf(checkoutDate);
        Date returnDateAsDate = Date.valueOf(returnDate);

        List<String> titles = getTitlesForCheckout(); // Get titles dynamically
        for (String title : titles) {
            ItemModel item = itemSearchController.getItemByTitle(title); // Assuming this method exists
            if (item != null) {
                String itemStatus = item.getItemStatus();
                if ("Available".equals(itemStatus) || "Reserver".equals(itemStatus)) {
                    // Let's fetch CheckoutID from the database
                    int checkoutId = checkoutDAO.getNextCheckoutID(); // Assuming a method to get the next available CheckoutID

                    CheckoutModel checkout = new CheckoutModel(checkoutId, checkoutDateAsDate, returnDateAsDate, "0", "Reserver", borrowerId);

                    boolean checkoutCreated = checkoutDAO.createCheckout(checkout);
                    if (checkoutCreated) {
                        int itemId = Integer.parseInt(item.getItemID()); // Convert item ID to integer
                        boolean statusUpdated = checkoutDAO.updateItemStatus(itemId, "Reserver");
                        if (!statusUpdated) {
                            return false; // If updating status fails, stop the process
                        }
                    } else {
                        return false; // If creating checkout fails, stop the process
                    }
                } else {
                    System.out.println("Item '" + title + "' cannot be checked out. Status: " + itemStatus);
                    return false; // If item status is not 'Available' or 'Reserved', stop the process
                }
            } else {
                System.out.println("Item '" + title + "' not found.");
                return false; // If item is not found, stop the process
            }
        }
        return true;
    }

    private List<String> getTitlesForCheckout() {
        // Use ItemSearchController to fetch titles dynamically
        return itemSearchController.searchItem(""); // Empty string to fetch all titles, you can pass a search criterion here if needed
    }
}
