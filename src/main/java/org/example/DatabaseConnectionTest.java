package org.example;

import java.sql.*;

public class DatabaseConnectionTest {
    public static void main(String[] args) {

        // TODO: move to .env
        String url = "jdbc:postgresql://localhost:5432/mydatabase";
        String user = "myuser";
        String password = "passw0rd";

        Connection connection = null;

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            Statement statement = null;

            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(
                    "SELECT * FROM profile"
            );
            while (result.next()) {
                System.out.println(result.getInt("id") + "\t" + result.getString("name") + "\t" + result.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        }
    }
}
