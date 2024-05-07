package com.javagrupp;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UserView {
    private Button searchBooksButton;
    // andra knappar för andra användarfunktioner...

    public UserView() {
        searchBooksButton = new Button("Sök efter böcker");
        // initiera andra knappar...

        VBox layout = new VBox(10);
        layout.getChildren().addAll(searchBooksButton /*, andra knappar... */);
    }

    // getters...
}
