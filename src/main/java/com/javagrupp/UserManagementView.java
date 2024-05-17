package com.javagrupp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class UserManagementView extends VBox {
    private ObservableList<UserModel> users;
    private ListView<UserModel> userListView;
    private Button makeAdminButton;
    private Button makeUserButton;
    private UserManagementController controller;

    public UserManagementView(List<UserModel> userList, UserManagementController controller) {
        this.controller = controller;
        this.users = FXCollections.observableArrayList(userList);

        userListView = new ListView<>(users);

        makeAdminButton = new Button("Make Admin");
        makeAdminButton.setOnAction(this::makeAdminButtonClicked);
        makeUserButton = new Button("Make User");
        makeUserButton.setOnAction(this::makeUserButtonClicked);

        getChildren().addAll(userListView, makeAdminButton, makeUserButton);
    }

    private void makeAdminButtonClicked(ActionEvent event) {
        UserModel selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Select a user first.");
            return;
        }

        controller.makeUserAdmin(selectedUser);
    }

    private void makeUserButtonClicked(ActionEvent event) {
        // Implement logic to make user a regular user
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
