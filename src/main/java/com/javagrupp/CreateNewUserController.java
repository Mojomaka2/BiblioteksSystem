package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateNewUserController {
    private CreateNewUserView newUserView;
    private CreateNewUserModel newUserModel;

    private static final int MIN_USERNAME_LENGTH = 6;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 30;

    public CreateNewUserController(CreateNewUserView newUserView, CreateNewUserModel newUserModel) {
        this.newUserView = newUserView;
        this.newUserModel = newUserModel;

        newUserView.getCreateButton().setOnAction(e -> createNewUserButtonClicked());
    }

    private void createNewUserButtonClicked() {
        String username = newUserView.getUsernameField().getText();
        String password = newUserView.getPasswordField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Användarnamn och lösenord måste anges");
            return;
        }

        if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            System.out.println("Användarnamnet måste vara mellan 6 och 20 tecken långt");
            return;
        }

        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            System.out.println("Lösenordet måste vara mellan 8 och 30 tecken långt");
            return;
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "java2");
            String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            System.out.println("Ny användare har skapats");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}