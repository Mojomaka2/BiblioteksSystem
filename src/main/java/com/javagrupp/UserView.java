package com.javagrupp;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserView {
    private Stage primaryStage;
    private VBox layout;

    public UserView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Label testLabel = new Label("Test");

        layout = new VBox(10);
        layout.getChildren().addAll(testLabel);
        
        // Set up the scene
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
    }

    public void show() {
        primaryStage.setTitle("User View");
        primaryStage.show();
    }
}
