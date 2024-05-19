package com.javagrupp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // Metod för att få en ny anslutning
    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> userList = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("UserID");
                String userName = resultSet.getString("UserName");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                String streetName = resultSet.getString("StreetName");
                int streetNumber = resultSet.getInt("StreetNumber");
                int zipCode = resultSet.getInt("ZipCode");
                String role = resultSet.getString("Role");

                UserModel user = new UserModel(userId, userName, firstName, lastName, phoneNumber, email, streetName, streetNumber, zipCode, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public UserModel getUser(String username) {
        String query = "SELECT * FROM User WHERE UserName = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userID = resultSet.getInt("UserID");
                    String userName = resultSet.getString("UserName");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    String email = resultSet.getString("Email");
                    String streetName = resultSet.getString("StreetName");
                    int streetNumber = resultSet.getInt("StreetNumber");
                    int zipCode = resultSet.getInt("ZipCode");
                    String role = resultSet.getString("Role");
    
                    return new UserModel(userID, userName, firstName, lastName, phoneNumber, email, streetName, streetNumber, zipCode, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if user not found
    }

    // Andra metoder för att hantera användardata...
}
