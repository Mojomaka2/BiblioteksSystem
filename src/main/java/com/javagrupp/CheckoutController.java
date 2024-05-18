package com.javagrupp;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CheckoutController {
    private CheckoutDAO checkoutDAO;
    private ItemSearchController itemSearchController;

    public CheckoutController(CheckoutDAO checkoutDAO) {
        this.checkoutDAO = checkoutDAO;
        this.itemSearchController = new ItemSearchController();
    }

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
}
