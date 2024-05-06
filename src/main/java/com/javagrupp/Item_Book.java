package com.javagrupp;

// Klass: Item_Book (ärver från Item)
public class Item_Book extends Item {
    private String author;
    private String ISBN;

    public Item_Book(String item_id, String title, String identifier, String item_status, String description, int item_stock, String author, String ISBN) {
        super(item_id, title, identifier, item_status, description, item_stock);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getISBN() {
        return this.ISBN;
    }

    // Andra bokspecifika metoder kan läggas till här...
}
