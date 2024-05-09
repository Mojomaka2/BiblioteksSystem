package com.javagrupp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginController {
    private LoginModel model;
    private LoginView view;
    private Stage primaryStage;

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
                AdminModel model = new AdminModel();
                AdminView view = new AdminView(primaryStage);
                new AdminController(model, view, primaryStage);
            } else {
                // Visa användarvy
                UserView userView = new UserView(primaryStage);
                userView.show();
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
    }
}
