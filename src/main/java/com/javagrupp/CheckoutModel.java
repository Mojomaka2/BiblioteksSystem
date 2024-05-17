package com.javagrupp;

import java.sql.Date;

public class CheckoutModel {
    private int checkoutID;
    private Date checkoutDate;
    private Date returnDate;
    private String fine;
    private String checkoutStatus;
    private int borrowerID;

    public CheckoutModel(int checkoutID, Date checkoutDate, Date returnDate, String fine, String checkoutStatus, int borrowerID) {
        this.checkoutID = checkoutID;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.checkoutStatus = checkoutStatus;
        this.borrowerID = borrowerID;
    }

    // Getters and setters
    public int getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(int checkoutID) {
        this.checkoutID = checkoutID;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(String checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public int getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }
}
