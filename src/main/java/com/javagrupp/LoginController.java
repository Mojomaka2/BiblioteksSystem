package com.javagrupp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

public class LoginController {
    private LoginModel model;
    private LoginView view;
    private Stage primaryStage;

    // JDBC-anslutningssträng
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String JDBC_USER = "root";
    static final String JDBC_PASSWORD = "java2";

    public LoginController(LoginModel model, LoginView view, Stage primaryStage) {
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
        System.err.println("Controller KOnstruktor");

        view.getLoginButton().setOnAction(e -> loginButtonClicked());
    }

    private void loginButtonClicked() {
        System.err.println("Knappfunkar");
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Förberedd SQL-fråga för att hämta användarinformation
            System.err.println("Success");
            String query = "SELECT UserID, role FROM user WHERE UserName=? AND Password=?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Sätt inmatade användarnamn och lösenord i SQL-frågan
            statement.setString(1, username);
            statement.setString(2, password);

            // Utför frågan
            ResultSet resultSet = statement.executeQuery();

            // Om användaren hittas i databasen
            if (resultSet.next()) {
                // Hämta användarroll från resultatet
                String role = resultSet.getString("role");

                // Visa AdminView om användaren är admin, annars Visa UserView
                if (role.equals("admin")) {
                    AdminView adminView = new AdminView(primaryStage);
                    adminView.show();
                } else {
                    //UserView userView = new UserView(primaryStage);
                    //userView.show();
                    System.err.println("bajs");
                }
            } else {
                // Visar ett felmeddelande om inloggningen misslyckas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fel vid inloggning");
                alert.setHeaderText(null);
                alert.setContentText("Fel användarnamn eller lösenord. Försök igen.");
                alert.showAndWait();

                // Rensar textfälten
                view.getUsernameField().clear();
                view.getPasswordField().clear();
            }
        } catch (SQLException e) {
            System.out.println("Ett SQLException inträffade: " + e.getMessage());
            e.printStackTrace();

            // Hantera eventuella SQL-anslutningsfel här
        }
    }
}
