package com.javagrupp;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button createNewUserButton; //knapp för att skapa ny användare
    private VBox layout;
    private Stage primaryStage;

    public LoginView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        usernameField = new TextField();
        usernameField.setPromptText("Användarnamn");

        passwordField = new PasswordField();
        passwordField.setPromptText("Lösenord");

        loginButton = new Button("Logga in");
        createNewUserButton = new Button("Skapa ny användare"); // Skapa ny användare knapp

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(usernameField, passwordField, loginButton, createNewUserButton);
    }

    public VBox getLayout() {
        return layout;
    }

    public void show() {
        Scene scene = new Scene(layout, 400, 160);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inloggning");
        primaryStage.show();
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getCreateNewUserButton() {
        return createNewUserButton;
    }
}
