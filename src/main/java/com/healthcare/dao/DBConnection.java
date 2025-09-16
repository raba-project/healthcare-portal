package com.healthcare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static volatile DBConnection instance;
    private static Connection connection;

    private final String url = "jdbc:mysql://localhost:3306/healthcare_portal?serverTimezone=UTC";
    private final String username = "root";
    private final String password = "admin$123";

    private DBConnection() throws SQLException {
        // Create initial connection
        connection = DriverManager.getConnection(url, username, password);
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DBConnection.class) {
                if (connection == null || connection.isClosed()) {
                    // Recreate connection if null or closed
                    DBConnection.getInstance();
                }
            }
        }
        return connection;
    }
}
