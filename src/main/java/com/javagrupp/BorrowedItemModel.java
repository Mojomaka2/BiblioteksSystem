package com.javagrupp;

import java.time.LocalDate;

public class BorrowedItemModel {
    private int itemId;
    private String title;
    private LocalDate returnDate;
    private String status;
    private String borrowerName;

    public BorrowedItemModel(int itemId, String title, LocalDate returnDate, String status, String borrowerName) {
        this.itemId = itemId;
        this.title = title;
        this.returnDate = returnDate;
        this.status = status;
        this.borrowerName = borrowerName;
    }

    // Getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowedItemModel{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                '}';
    }
}
