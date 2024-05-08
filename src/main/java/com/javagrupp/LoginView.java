package com.javagrupp;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private VBox layout;
    private Stage primaryStage;

    public LoginView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        usernameField = new TextField();
        usernameField.setPromptText("Användarnamn");

        passwordField = new PasswordField();
        passwordField.setPromptText("Lösenord");

        loginButton = new Button("Logga in");
        loginButton.setOnAction(e -> loginButtonClicked());

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(usernameField, passwordField, loginButton);
    }

    private void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("admin")) {
            // Show AdminView if username and password are admin
            AdminView adminView = new AdminView(primaryStage); // Pass primaryStage to AdminView constructor
            adminView.show();
        } else {
            // Show error message if login fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fel vid inloggning");
            alert.setHeaderText(null);
            alert.setContentText("Fel användarnamn eller lösenord. Försök igen.");
            alert.showAndWait();

            // Clear text fields
            usernameField.clear();
            passwordField.clear();
        }
    }

    public VBox getLayout() {
        return layout;
    }

    public void show() {
        Scene scene = new Scene(layout);
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
}
