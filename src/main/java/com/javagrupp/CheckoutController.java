package com.javagrupp;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CheckoutController {
    private CheckoutDAO checkoutDAO;
    private ItemSearchController itemSearchController;

    // Constructor that takes a CheckoutDAO
    public CheckoutController(CheckoutDAO checkoutDAO) {
        this.checkoutDAO = checkoutDAO;
        this.itemSearchController = new ItemSearchController();
    }

    // Constructor that takes a Connection
    public CheckoutController(Connection connection) {
        this.checkoutDAO = new CheckoutDAO(connection);
        this.itemSearchController = new ItemSearchController();
    }

    // Method that checks out items using return dates for each item
    public boolean checkoutItems(LocalDate checkoutDate, Map<String, LocalDate> returnDates, int borrowerId) {
        // Check current borrowed items
        int maxItems = checkoutDAO.getMaxItemsForBorrower(borrowerId);
        int currentCheckedOutItems = checkoutDAO.getCurrentCheckedOutItemsCount(borrowerId);

        if (currentCheckedOutItems + returnDates.size() > maxItems) {
            System.out.println("Cannot checkout items. Borrower exceeds max allowed items.");
            return false;
        }

        for (Map.Entry<String, LocalDate> entry : returnDates.entrySet()) {
            String title = entry.getKey();
            LocalDate returnDate = entry.getValue();
            Date returnDateAsDate = Date.valueOf(returnDate);

            ItemModel item = itemSearchController.getItemByTitle(title);
            if (item != null) {
                String itemStatus = item.getItemStatus();
                if ("Available".equals(itemStatus) || "Reserver".equals(itemStatus)) {
                    int checkoutId = checkoutDAO.getNextCheckoutID();
                    CheckoutModel checkout = new CheckoutModel(checkoutId, Date.valueOf(checkoutDate), returnDateAsDate, "0", "Reserver", borrowerId);

                    boolean checkoutCreated = checkoutDAO.createCheckout(checkout);
                    if (checkoutCreated) {
                        int itemId = Integer.parseInt(item.getItemID());
                        boolean statusUpdated = checkoutDAO.updateItemStatus(itemId, "Reserver");
                        if (!statusUpdated) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    System.out.println("Item '" + title + "' cannot be checked out. Status: " + itemStatus);
                    return false;
                }
            } else {
                System.out.println("Item '" + title + "' not found.");
                return false;
            }
        }
        return true;
    }

    // Method that checks out items with a single return date for all items
    public boolean checkoutItems(LocalDate checkoutDate, LocalDate returnDate, int borrowerId) {
        // Convert LocalDate to Date
        Date checkoutDateAsDate = Date.valueOf(checkoutDate);
        Date returnDateAsDate = Date.valueOf(returnDate);

        // Check current borrowed items
        int maxItems = checkoutDAO.getMaxItemsForBorrower(borrowerId);
        int currentCheckedOutItems = checkoutDAO.getCurrentCheckedOutItemsCount(borrowerId);

        if (currentCheckedOutItems >= maxItems) {
            System.out.println("Cannot checkout items. Borrower exceeds max allowed items.");
            return false;
        }

        List<String> itemsToCheckout = // get items to checkout (implementation depends on your application logic)
        for (String title : itemsToCheckout) {
            ItemModel item = itemSearchController.getItemByTitle(title);
            if (item != null) {
                String itemStatus = item.getItemStatus();
                if ("Available".equals(itemStatus) || "Reserver".equals(itemStatus)) {
                    int checkoutId = checkoutDAO.getNextCheckoutID();
                    CheckoutModel checkout = new CheckoutModel(checkoutId, checkoutDateAsDate, returnDateAsDate, "0", "Reserver", borrowerId);

                    boolean checkoutCreated = checkoutDAO.createCheckout(checkout);
                    if (checkoutCreated) {
                        int itemId = Integer.parseInt(item.getItemID());
                        boolean statusUpdated = checkoutDAO.updateItemStatus(itemId, "Reserver");
                        if (!statusUpdated) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    System.out.println("Item '" + title + "' cannot be checked out. Status: " + itemStatus);
                    return false;
                }
            } else {
                System.out.println("Item '" + title + "' not found.");
                return false;
            }
        }
        return true;
    }
}