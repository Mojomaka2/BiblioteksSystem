package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateNewUserView {
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField phoneNumberField;
    private TextField emailField;
    private TextField streetNumberField;
    private TextField streetNameField;
    private TextField zipCodeField;
    private Button createButton;
    private VBox layout;
    private Stage primaryStage;

    public CreateNewUserView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        usernameField = new TextField();
        usernameField.setPromptText("Användarnamn");

        passwordField = new PasswordField();
        passwordField.setPromptText("Lösenord");

        firstNameField = new TextField();
        firstNameField.setPromptText("Förnamn");

        lastNameField = new TextField();
        lastNameField.setPromptText("Efternamn");

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Telefonnummer");

        emailField = new TextField();
        emailField.setPromptText("Email");

        streetNumberField = new TextField();
        streetNumberField.setPromptText("Gatunummer");

        streetNameField = new TextField();
        streetNameField.setPromptText("Gatunamn");

        zipCodeField = new TextField();
        zipCodeField.setPromptText("Postnummer");

        createButton = new Button("Skapa Användare");

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll
            (usernameField, passwordField, firstNameField, lastNameField, phoneNumberField, emailField, streetNumberField, streetNameField, zipCodeField, createButton);
    }

    public void show() {
        Scene scene = new Scene(layout, 400, 380);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Skapa ny användare");
        primaryStage.show();
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getStreetNumberField() {
        return streetNumberField;
    }

    public TextField getStreetNameField() {
        return streetNameField;
    }

    public TextField getZipCodeField() {
        return zipCodeField;
    }

    public Button getCreateButton() {
        return createButton;
    }
}
