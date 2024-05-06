package com.javagrupp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application{
    private TextField usernameField;
    private PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) {
        // Skapa användargränssnittet
        Label usernameLabel = new Label("Användarnamn:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Lösenord:");
        passwordField = new PasswordField();
        Button loginButton = new Button("Logga in");
        loginButton.setOnAction(e -> loginButtonClicked(primaryStage));

        // Lägg till komponenter till en layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        // Visa layouten i ett fönster
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inloggning");
        primaryStage.show();
    }

    private void loginButtonClicked(Stage secondaryStage) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Implementera inloggningslogiken här
        AddItems addItems = new AddItems();
        addItems.start(secondaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

