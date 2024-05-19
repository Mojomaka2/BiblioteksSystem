package com.javagrupp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USER = "root";
    public static final String PASS = "java2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
