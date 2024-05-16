package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CreateNewUserController {
    private CreateNewUserView newUserView;
    private CreateNewUserModel newUserModel;
    private Stage primaryStage;

    private static final int MIN_USERNAME_LENGTH = 6;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 30;

    public CreateNewUserController(CreateNewUserView newUserView, CreateNewUserModel newUserModel, Stage primaryStage) {
        this.newUserView = newUserView;
        this.newUserModel = newUserModel;
        this.primaryStage = primaryStage;

        newUserView.getCreateButton().setOnAction(e -> createNewUserButtonClicked());
    }

    private void createNewUserButtonClicked() {
        String username = newUserView.getUsernameField().getText();
        String password = newUserView.getPasswordField().getText();
        String firstname = newUserView.getFirstNameField().getText();
        String lastname = newUserView.getLastNameField().getText();
        String phonenumber = newUserView.getPhoneNumberField().getText();
        String email = newUserView.getEmailField().getText();
        String streetname = newUserView.getStreetNameField().getText();
        String streetnumber = newUserView.getStreetNumberField().getText();
        String zipcode = newUserView.getZipCodeField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fel vid skapandet av användare");
            alert.setHeaderText(null);
            alert.setContentText("Användarnamn och lösenord måste anges");
            alert.showAndWait();
        }

        else if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fel vid skapandet av användare");
            alert.setHeaderText(null);
            alert.setContentText("Användarnamnet måste vara mellan 6 och 20 tecken långt");
            alert.showAndWait();
        }

        else if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fel vid skapandet av användare");
            alert.setHeaderText(null);
            alert.setContentText("Lösenordet måste vara mellan 8 och 30 tecken långt");
            alert.showAndWait();
        }

        else{
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "java2");
                String sql = "INSERT INTO User (UserName, Password, FirstName, LastName, PhoneNumber, Email, StreetName, StreetNumber, ZipCode, DateCreated, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), 'user')";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, firstname);
                pstmt.setString(4, lastname);
                pstmt.setString(5, phonenumber);
                pstmt.setString(6, email);
                pstmt.setString(7, streetname);
                pstmt.setString(8, streetnumber);
                pstmt.setString(9, zipcode);

                pstmt.executeUpdate();
                pstmt.close();
                connection.close();
                System.out.println("Ny användare har skapats");

                // Visa popup-meddelande om att användaren har skapats
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Användare skapad");
                successAlert.setHeaderText(null);
                successAlert.setContentText("En ny användare har skapats!");
                successAlert.showAndWait();

                clearAllFields();
            } 
        
            catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Databasfel");
                alert.setHeaderText(null);
                alert.setContentText("Ett fel inträffade vid anslutning till databasen. Försök igen.");
                alert.showAndWait();
            }
        }
    }

    private void clearAllFields() {
        newUserView.getUsernameField().clear();
        newUserView.getPasswordField().clear();
        newUserView.getFirstNameField().clear();
        newUserView.getLastNameField().clear();
        newUserView.getPhoneNumberField().clear();
        newUserView.getEmailField().clear();
        newUserView.getStreetNameField().clear();
        newUserView.getStreetNumberField().clear();
        newUserView.getZipCodeField().clear();
    }
}