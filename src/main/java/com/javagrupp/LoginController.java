package com.javagrupp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginController {
    private LoginModel model;
    private LoginView view;
    private Stage primaryStage;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

    public LoginController(LoginModel model, LoginView view, Stage primaryStage) {
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;

        // Add listener for button click in the view
        view.getLoginButton().setOnAction(e -> loginButtonClicked());
    }

    private void loginButtonClicked() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        // Perform authentication in the model
        boolean authenticated = model.authenticate(username, password);

        if (authenticated) {
            // Show the appropriate view based on user role
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                UserDAO userDAO = new UserDAO();
                UserModel currentUser = userDAO.getUser(username);
                if (currentUser != null) {
                    if (currentUser.getRole().equals("admin")) {
                        AdminView adminView = new AdminView(primaryStage, userDAO, currentUser);
                        AdminController adminController = new AdminController(adminView, userDAO, currentUser);
                        adminView.show();
                    } else {
                        // Show user view
                        UserView userView = new UserView(primaryStage);
                        userView.show();
                    }
                } else {
                    showAlert("User not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Database connection failed.");
            }
        } else {
            // Show an error message if login fails
            showAlert("Incorrect username or password. Please try again.");
            // Clear the text fields
            view.getUsernameField().clear();
            view.getPasswordField().clear();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
