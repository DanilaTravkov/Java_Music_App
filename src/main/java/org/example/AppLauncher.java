package org.example;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import controller.AlbumController;
import controller.ArtistController;
import controller.BandController;
import controller.ProfileController;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

import io.github.cdimascio.dotenv.Dotenv;
import model.Album;
import view.AppView;

public class AppLauncher {
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
            ProfileController profileController = new ProfileController(connection); // Profile controller instance
            BandController bandController = new BandController(connection);
            ArtistController artistController = new ArtistController(connection);
            AlbumController albumController = new AlbumController(connection);
            setUIFont(new Font("Arial", Font.PLAIN, 14));
            setUILF();
            new AppView(connection, profileController, bandController, albumController, artistController);

        } catch (SQLException e) {
            System.out.println(System.getenv("DB_HOST"));
            System.out.println("SQL Exception occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
    }

    public static void setUIFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("List.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ToolTip.font", font);
        // Add more UI components as needed
    }

    public static void setUILF() {
        try {
//            UIManager.setLookAndFeel(FlatDarkPurpleIJTheme.class.getCanonicalName());
            UIManager.setLookAndFeel(FlatNightOwlIJTheme.class.getCanonicalName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
