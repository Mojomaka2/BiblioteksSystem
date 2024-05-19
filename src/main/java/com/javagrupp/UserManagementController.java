package com.javagrupp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserManagementController {
    private UserManagementView view;
    private Stage primaryStage;
    private List<UserModel> userList;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "java2";

    public UserManagementController(List<UserModel> userList, Stage primaryStage) {
        this.userList = userList;
        this.primaryStage = primaryStage;
        this.view = new UserManagementView(userList, this);
    }

    public void makeUserAdmin(UserModel user) {
        // Update the database to make the user an admin
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE User SET role = 'admin' WHERE UserID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user.getUserID());
            pstmt.executeUpdate();
            pstmt.close();
    
            // Generate a staffId for the user if they are made an admin
            if (user.getRole().equals("admin")) {
                String generateStaffIdSql = "INSERT INTO Staff (UserID) VALUES (?)";
                PreparedStatement generateStaffIdPstmt = connection.prepareStatement(generateStaffIdSql);
                generateStaffIdPstmt.setInt(1, user.getUserID());
                generateStaffIdPstmt.executeUpdate();
                generateStaffIdPstmt.close();
            }
    
            showAlert("User '" + user.getUserName() + "' has been made an admin.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error occurred while making user an admin.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public UserManagementView getView() {
        return view;
    }
    
}
