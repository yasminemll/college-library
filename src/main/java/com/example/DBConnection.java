package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de la connexion
    private static final String USER = "biblio";
    private static final String PASSWORD = "biblio123";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connected to Oracle DB.");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ JDBC Driver not found.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 DB connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
