package com.javagrupp;

// Klass: Item_DVD (ärver från Item)
public class DVDModel extends ItemModel {
    private String director;
    private String genre;

    public DVDModel(String item_id, String title, String identifier, String item_status, String description, int item_stock, String director, String genre) {
        super(item_id, title, identifier, item_status, description, item_stock);
        this.director = director;
        this.genre = genre;
    }

    public String getDirector() {
        return this.director;
    }

    public String getGenre() {
        return this.genre;
    }

    // Andra DVD-specifika metoder kan läggas till här...
}