package com.javagrupp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Skapa vyn och kontrollern
        LoginView view = new LoginView();
        LoginController controller = new LoginController(new LoginModel(null, null), view, primaryStage);

        // Visa användargränssnittet
        Scene scene = new Scene(view.getLayout(), 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inloggning");
        primaryStage.show();
    }
}
