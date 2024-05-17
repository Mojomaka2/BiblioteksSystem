package com.javagrupp;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class CheckoutController {
    private CheckoutDAO checkoutDAO;
    private ItemSearchController itemSearchController;

    public CheckoutController(Connection connection) {
        this.checkoutDAO = new CheckoutDAO(connection);
        this.itemSearchController = new ItemSearchController(); // Assuming this controller can get item details
    }

    public boolean checkoutItems(List<String> titles, int borrowerId, int staffId) {
        for (String title : titles) {
            ItemModel item = itemSearchController.getItemByTitle(title); // Assuming this method exists
            if (item != null) {
                String itemStatus = item.getItemStatus();
                if ("Available".equals(itemStatus) || "Reserved".equals(itemStatus)) {
                    CheckoutModel checkout = new CheckoutModel(new Date(), calculateReturnDate(), "0", "Reserved", borrowerId, staffId);

                    boolean checkoutCreated = checkoutDAO.createCheckout(checkout);
                    if (checkoutCreated) {
                        int itemId = Integer.parseInt(item.getItemID()); // Convert item ID to integer
                        boolean statusUpdated = checkoutDAO.updateItemStatus(itemId, "Reserved");
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

    private Date calculateReturnDate() {
        // Logic to calculate the return date
        // For example, add 14 days to current date
        return new Date(System.currentTimeMillis() + (14L * 24 * 60 * 60 * 1000)); // 14 days later
    }
}
