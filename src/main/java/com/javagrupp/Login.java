package com.javagrupp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
    private Stage primaryStage;
    private UserView view;
    private UserController controller;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Skapa vyn och kontrollern
        view = new UserView();
        controller = new UserController(new User(null, null), view, primaryStage);

        // Visa användargränssnittet
        Scene scene = new Scene(view.getLayout(), 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inloggning");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
