package com.javagrupp;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
public void start(Stage primaryStage) {
    LoginModel model = new LoginModel(null, null, null); // Skapa en instans av LoginModel
    LoginView view = new LoginView(primaryStage); // Skapa en instans av LoginView
    new LoginController(model, view, primaryStage); // Skapa en instans av LoginController

    view.show(); // Visa LoginView
}

    public static void main(String[] args) {
        launch(args);
    }
}
