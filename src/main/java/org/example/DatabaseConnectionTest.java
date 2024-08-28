package org.example;

import java.sql.*;
import java.util.List;

import controller.ProfileController;
import io.github.cdimascio.dotenv.Dotenv;
import model.Profile;

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

            ProfileController profileController = new ProfileController(connection);
            List<Profile> profiles = profileController.listProfiles();
            for (int i = 0; i < profiles.toArray().length; i++) {
                System.out.println(profiles.get(i).getUsername());
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
