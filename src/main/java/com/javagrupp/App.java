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

        // Initialize the create new user view, model, and controller
        CreateNewUserModel newUserModel = new CreateNewUserModel();
        CreateNewUserView newUserView = new CreateNewUserView(primaryStage);
        CreateNewUserController newUserController = new CreateNewUserController(newUserView, newUserModel, primaryStage);

        // Set the createNewUserButton action to show the create new user view
        view.getCreateNewUserButton().setOnAction(e -> newUserView.show());

        // Display the login view
        view.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}