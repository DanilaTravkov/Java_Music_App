package org.example;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnectionTest {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        String url = "jdbc:postgresql://" + dotenv.get("DB_HOST") + ":5432/mydatabase";
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

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
            System.out.println(System.getenv("DB_HOST"));
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
