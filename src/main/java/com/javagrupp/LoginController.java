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

        // Lägg till lyssnare för knappklick i vyen
        view.getLoginButton().setOnAction(e -> loginButtonClicked());
    }

    private void loginButtonClicked() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        // Utför autentisering i modellen
        boolean authenticated = model.authenticate(username, password);

        if (authenticated) {
            // Visa rätt vy beroende på användarroll
            if (model.getRole().equals("admin")) {
                AdminModel adminModel = new AdminModel();
                try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                    UserDAO userDAO = new UserDAO();
                    AdminView adminView = new AdminView(primaryStage, userDAO); // Uppdaterad konstruktor för AdminView
                    new AdminController(adminModel, adminView, primaryStage); // Uppdaterad konstruktor för AdminController
                    adminView.show();
                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert("Database connection failed.");
                }
            } else {
                // Visa användarvy
                UserView userView = new UserView(primaryStage);
                userView.show();
            }
        } else {
            // Visar ett felmeddelande om inloggningen misslyckas
            showAlert("Fel användarnamn eller lösenord. Försök igen.");
            // Rensar textfälten
            view.getUsernameField().clear();
            view.getPasswordField().clear();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fel vid inloggning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
