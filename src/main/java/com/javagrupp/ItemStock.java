package com.javagrupp;

public class ItemStock {
    private int stockID;
    private int amount;
    private int itemID;

    public ItemStock(int stockID, int amount, int itemID) {
        this.stockID = stockID;
        this.amount = amount;
        this.itemID = itemID;
    }

    // Getters and setters
    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
