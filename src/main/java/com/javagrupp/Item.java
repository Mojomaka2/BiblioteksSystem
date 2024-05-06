package com.javagrupp;

public abstract class Item {
    private String item_id;
    private String title;
    private String identifier;
    private String item_status;
    private String description;
    private int item_stock;

    public Item(String item_id, String title, String identifier, String item_status, String description, int item_stock) {
        this.item_id = item_id;
        this.title = title;
        this.identifier = identifier;
        this.item_status = item_status;
        this.description = description;
        this.item_stock = item_stock;
    }

    public String getItemId() {
        return this.item_id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getItemStatus() {
        return this.item_status;
    }

    public void setItemStatus(String item_status) {
        this.item_status = item_status;
    }

    public String getDescription() {
        return this.description;
    }

    public int getItemStock() {
        return this.item_stock;
    }

    public void setItemStock(int item_stock) {
        this.item_stock = item_stock;
    }

    // Andra gemensamma metoder kan läggas till här...
}

