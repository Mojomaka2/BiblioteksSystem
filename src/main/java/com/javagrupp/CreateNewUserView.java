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
    private Button createButton;
    private VBox layout;
    private Stage primaryStage;

    public CreateNewUserView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        usernameField = new TextField();
        usernameField.setPromptText("Användarnamn");

        passwordField = new PasswordField();
        passwordField.setPromptText("Lösenord");

        createButton = new Button("Skapa Användare");

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(usernameField, passwordField, createButton);
    }

    public void show() {
        Scene scene = new Scene(layout, 400, 150);
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

    public Button getCreateButton() {
        return createButton;
    }
}
