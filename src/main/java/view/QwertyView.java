package view;

import controller.ProfileController;
import session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QwertyView extends javax.swing.JFrame {
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton signInButton;
    private JButton signUpButton;
    private JPanel cardPanel;
    private JPanel signInPanel;
    private JPanel signUpPanel;
    private JPanel albumsPanel;
    private JPanel artistsPanel;
    private JPanel bandsPanel;
    private JLabel artistPlaceholder;
    private JLabel albumsPlaceholder;
    private JLabel bandsPlaceholder;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel loginLabel;
    private JButton registerButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField textField6;
    private JPasswordField passwordField1;
    private JComboBox genderBox;
    private JLabel signUpLabel;
    private JLabel nameFieldLabel;
    private JLabel surnameFieldLabel;
    private JLabel emailFieldLabel;
    private JLabel phoneNumberFieldLabel;
    private JLabel genderFieldLabel;
    private JLabel usernamefieldLabel;
    private JLabel passwrodFieldLabel;
    private JLabel usernameFieldLabel2;
    private JLabel passwordFieldLabel2;
    private JPanel highlightsPanel;
    private JLabel highlightsPlaceholder;
    private JButton highlightsSidebarButton;
    private JButton artistsSidebarButton;
    private JButton bandsSidebarButton;
    private JButton albumsSideBarButton;
    private JPanel sidebarPanel;
    private JPanel headerPanel;
    private JPanel searchResultsPanel;
    private JLabel searchResultsPlaceholder;

    public QwertyView() {

        this.setTitle("Login");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.addLayoutComponent(signInPanel, "SIGNIN");
        cardLayout.addLayoutComponent(signUpPanel, "SIGNUP");
        cardLayout.addLayoutComponent(albumsPanel, "ALBUMS");
        cardLayout.addLayoutComponent(artistsPanel, "ARTISTS");
        cardLayout.addLayoutComponent(bandsPanel, "BANDS");
        cardLayout.addLayoutComponent(highlightsPanel, "HIGHLIGHTS");
        cardLayout.addLayoutComponent(searchResultsPanel, "SEARCHRESULTS");

        cardLayout.show(cardPanel, "HIGHLIGHTS");

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SIGNIN");
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SIGNUP");
            }
        });
        highlightsSidebarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HIGHLIGHTS");
            }
        });
        artistsSidebarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artistPlaceholder.setText(Session.getInstance().getLoggedInUser().getUsername());
                cardLayout.show(cardPanel, "ARTISTS");

            }
        });
        bandsSidebarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "BANDS");
            }
        });
        albumsSideBarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ALBUMS");
            }
        });
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SEARCHRESULTS");
            }
        });
    }
}
