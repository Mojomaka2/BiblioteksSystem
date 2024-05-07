package com.javagrupp;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private VBox layout;
    
    public LoginView() {
        usernameField = new TextField();
        usernameField.setPromptText("Användarnamn");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Lösenord");
        
        loginButton = new Button("Logga in");
        
        layout = new VBox(10); // Vertical box med 10px mellanrum
        layout.getChildren().addAll(usernameField, passwordField, loginButton);
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
    
    public VBox getLayout() {
        return layout;
    }
}
