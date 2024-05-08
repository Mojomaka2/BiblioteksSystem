package com.javagrupp;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView(primaryStage);
        loginView.show(); // Added missing show() method
    }

    public static void main(String[] args) {
        launch(args);
    }
}
