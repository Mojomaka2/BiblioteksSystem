package com.javagrupp;

import java.util.List;

public class ItemModel {
    private String itemID;
    private String title;
    private String identifier;
    private String itemStatus;
    private String description;
    private int itemStock;

    public ItemModel(String itemID, String title, String identifier, String itemStatus, String description, int itemStock) {
        this.itemID = itemID;
        this.title = title;
        this.identifier = identifier;
        this.itemStatus = itemStatus;
        this.description = description;
        this.itemStock = itemStock;
    }

    // Getters and setters
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }
   
}
