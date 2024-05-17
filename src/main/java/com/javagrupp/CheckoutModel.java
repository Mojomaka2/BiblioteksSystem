package com.javagrupp;

import java.util.Date;

public class CheckoutModel {
    private Date checkoutDate;
    private Date returnDate;
    private String fine;
    private String checkoutStatus;
    private int borrowerID;
    private int staffID;

    public CheckoutModel(Date checkoutDate, Date returnDate, String fine, String checkoutStatus, int borrowerID, int staffID) {
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.checkoutStatus = checkoutStatus;
        this.borrowerID = borrowerID;
        this.staffID = staffID;
    }

    // Getters and setters
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

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
