package com.javagrupp;

import javafx.stage.Stage;

public class AdminController {
    private AdminModel model;
    private AdminView view;
    private Stage primaryStage;

    public AdminController(AdminModel model, AdminView view, Stage primaryStage) {
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
    
        // Set event handler for add item button
        view.getAddItemButton().setOnAction(e -> addItemButtonClicked());
    }
    
    private void addItemButtonClicked() {
        // Handle what happens when the "Lägg till artikel" button is clicked
        System.err.println("klickadknapp");
        ItemAddView addItemView = new ItemAddView(); // Create an instance of ItemAddView
        addItemView.show(); 
    }
}
