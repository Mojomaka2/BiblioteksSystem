package com.javagrupp;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize the login view, model, and controller
        LoginModel model = new LoginModel();
        LoginView view = new LoginView(primaryStage);
        new LoginController(model, view, primaryStage);

        // Display the login view
        view.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
