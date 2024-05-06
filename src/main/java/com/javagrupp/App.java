package com.javagrupp;


import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Login login = new Login();
        login.start(primaryStage);
    }
}
