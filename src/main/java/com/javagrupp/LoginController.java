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
        
        view.getLoginButton().setOnAction(e -> loginButtonClicked());
    }
    
    private void loginButtonClicked() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        if (username.equals("admin") && password.equals("admin")) {
            // Visa AdminView om användarnamn och lösenord är admin
            AdminView adminView = new AdminView(primaryStage);
            adminView.show();
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
