package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    // JDBC-anslutningssträng
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "java2";

    private String username;
    private String password;
    private String role;

    public LoginModel() {
        // Tom konstruktor
    }

    public boolean authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Förberedd SQL-fråga för att hämta användarinformation
            String query = "SELECT role FROM user WHERE UserName=? AND Password=?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Sätt inmatade användarnamn och lösenord i SQL-frågan
            statement.setString(1, username);
            statement.setString(2, password);

            // Utför frågan
            ResultSet resultSet = statement.executeQuery();

            // Om användaren hittas i databasen
            if (resultSet.next()) {
                // Hämta användarroll från resultatet
                this.username = username;
                this.password = password;
                this.role = resultSet.getString("role");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Getters och setters för användarnamn, lösenord och roll
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
