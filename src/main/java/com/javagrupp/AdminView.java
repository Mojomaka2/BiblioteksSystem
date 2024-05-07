package com.javagrupp;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AdminView {
    private Button addItemButton;
    // andra knappar för andra adminfunktioner...

    public AdminView() {
        addItemButton = new Button("Lägg till artikel");
        // initiera andra knappar...

        VBox layout = new VBox(10);
        layout.getChildren().addAll(addItemButton /*, andra knappar... */);
    }

    // getters...
}
