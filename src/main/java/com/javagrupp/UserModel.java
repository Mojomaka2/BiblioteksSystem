package com.javagrupp;

public class UserModel {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String streetName;
    private int streetNumber;
    private int zipCode;
    private String role;

    public UserModel(int userID, String userName, String firstName, String lastName, String phoneNumber, String email, String streetName, int streetNumber, int zipCode, String role) {
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.role = role;
    }

    // Getters
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", zipCode=" + zipCode +
                ", role='" + role + '\'' +
                '}';
    }
}
