package com.javagrupp;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorrowedItemsView extends VBox {
    private ListView<BorrowedItemModel> listView;

    public BorrowedItemsView(Stage primaryStage, ObservableList<BorrowedItemModel> borrowedItems) {
        listView = new ListView<>(borrowedItems);
        getChildren().add(listView);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setScene(new Scene(this, 400, 600));
        stage.setTitle("Borrowed Items");
        stage.show();
    }
    
}

    

   


