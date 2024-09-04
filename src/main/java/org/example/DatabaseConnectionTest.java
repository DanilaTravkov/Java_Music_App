package org.example;

import controller.ProfileController;
import model.Profile;
import model.User;
import session.Session;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;
import view.MainView;
import view.QwertyView;

import javax.swing.*;

public class DatabaseConnectionTest {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        String envURL = "jdbc:postgresql://" + dotenv.get("DB_HOST") + ":5432/mydatabase";
        String envUser = dotenv.get("DB_USER");
        String envPassword = dotenv.get("DB_PASSWORD");

        Connection connection = null;

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish connection
            connection = DriverManager.getConnection(envURL, envUser, envPassword);
            System.out.println("Connection successful!");

            Scanner scanner = new Scanner(System.in); // Scanner
            User user = Session.getInstance().getLoggedInUser(); // Session instance
            ProfileController profileController = new ProfileController(connection); // Profile controller instance

            // Starting point of the application

            if (user != null) {
                System.out.printf("Welcome %s", user.getUsername());
            } else {
//                SwingUtilities.invokeLater(() -> { new QwertyView();}); // TODO: remove
                System.out.println("Welcome, please log in");

                System.out.println("Enter username: ");
                String loginUsername = scanner.nextLine();
                System.out.println("Enter password: ");
                String loginPassword = scanner.nextLine();

                Profile profile = profileController.getProfile(loginUsername);
                if (profile != null) {
                    if (Objects.equals(profile.getPassword(), loginPassword)) {
                        User loggedInUser = new User(profile.getUsername(), profile.getPassword(), profile.getEmail(), profile.getRole());
                        Session.getInstance().setLoggedInUser(loggedInUser);

                        System.out.printf("Welcome %s\n", loggedInUser.getUsername());
                        System.out.println("You are now logged in");

                        SwingUtilities.invokeLater(() -> { new QwertyView();});

//                        System.out.println("Confirm to delete user y/n: ");
//                        String answer = scanner.nextLine();
//                        if (Objects.equals(answer, "y")) {
//                            profileController.deleteProfile(Session.getInstance().getLoggedInUser().getUsername());
//                        } else if (Objects.equals(answer, "n")) {
//                            System.out.println("Aborted");
//                        }

//                        System.out.println("Enter updated username: ");
//                        String updatedUsername = scanner.nextLine();
//                        System.out.println("Enter updated email: ");
//                        String updatedEmail = scanner.nextLine();
//
//                        User updatedUser = new User(updatedUsername, null, updatedEmail);
//
//                        profileController.updateUser(updatedUser, Session.getInstance().getLoggedInUser().getUsername());
//                        System.out.println("User updated successfully");

                    }
                }
                else {
                    System.out.println("Wrong username or password");
                }

            }

//            List<Profile> profiles = profileController.listProfiles();
//            for (int i = 0; i < profiles.toArray().length; i++) {
//                System.out.println(profiles.get(i).getUsername() + "\t" + profiles.get(i).getEmail());
//            }

        } catch (SQLException e) {
            System.out.println(System.getenv("DB_HOST"));
            System.out.println("SQL Exception occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
//        finally {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//        }
    }
}
