package com.javagrupp;

import javafx.stage.Stage;

public class UserController {
    private AdminModel model;
    private AdminView view;
    private Stage primaryStage;

    public UserController(AdminModel model, AdminView view, Stage primaryStage) {
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
    
        
    }
    
    
}