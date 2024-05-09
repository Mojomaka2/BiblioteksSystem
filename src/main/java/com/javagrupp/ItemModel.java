package com.javagrupp;

public class ItemModel {
    private String item_id;
    private String title;
    private String identifier;
    private String item_status;
    private String description;
    private int item_stock;

    public ItemModel(String item_id, String title, String identifier, String item_status, String description, int item_stock) {
        this.item_id = item_id;
        this.title = title;
        this.identifier = identifier;
        this.item_status = item_status;
        this.description = description;
        this.item_stock = item_stock;
    }

    // Getter and setter methods...
}
