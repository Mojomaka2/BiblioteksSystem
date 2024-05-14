package com.javagrupp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ItemSearchView extends Stage{
    private ItemSearchController controller;
    private ListView<String> resultList;

    public ItemSearchView() {
        controller = new ItemSearchController();

        TextField searchField = new TextField();
        searchField.setPromptText("Sök boktitel");
        Button searchButton = new Button("Sök");

        resultList = new ListView<>();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(searchField, searchButton, resultList);

        searchButton.setOnAction(e -> searchButtonClicked(searchField.getText()));

        Scene scene = new Scene(layout, 400, 600);
        setScene(scene);
        setTitle("Sök boktitel");
    }

    private void searchButtonClicked(String title) {
        List<String> matchingTitles = controller.searchItem(title);
        ObservableList<String> items = FXCollections.observableArrayList(matchingTitles);
        resultList.setItems(items);
    }

}
    
