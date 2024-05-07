package com.javagrupp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class UserController {
    private UserModel model;
    private UserView view;
    private Stage primaryStage;
    
    public UserController(UserModel model, UserView view, Stage primaryStage) {
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
        
        view.getLoginButton().setOnAction(e -> loginButtonClicked());
    }
    
    private void loginButtonClicked() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();

        if (username.equals("admin") && password.equals("admin")) {
            // Öppna en ny vy eller gör andra relevanta åtgärder för en administratör
            // Till exempel, öppna en vy för att lägga till objekt
            ItemAddView addItemView = new ItemAddView();
            addItemView.show();
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
