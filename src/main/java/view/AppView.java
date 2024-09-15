package view;

import controller.BandController;
import controller.ProfileController;
import model.Band;
import model.Profile;
import model.User;
import session.Session;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class AppView extends javax.swing.JFrame {
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
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JComboBox genderBox;
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
    private JButton highlightsSidebarButton;
    private JButton artistsSidebarButton;
    private JButton bandsSidebarButton;
    private JButton albumsSideBarButton;
    private JPanel sidebarPanel;
    private JPanel headerPanel;
    private JPanel searchResultsPanel;
    private JLabel searchResultsPlaceholder;
    private JButton profileButton;
    private JButton logOutButton;
    private JPanel buttonsCardPanel;
    private JPanel unloggedInButtons;
    private JPanel loggedInButtons;
    private JPanel profilePanel;
    private JLabel artistIcon1;
    private JLabel artistIcon2;
    private JLabel artistImage4;
    private JLabel artistImage3;
    private JTextField profileNameField;
    private JTextField profileSurnameField;
    private JTextField profileGenderField;
    private JTextField profileBirthField;
    private JTextField profilePhoneField;
    private JLabel albumLogo1;
    private JLabel albumLogo2;
    private JLabel albumLogo3;
    private JLabel albumLogo4;
    private JLabel bandImage1;
    private JLabel bandImage2;
    private JLabel profileNameLabel;
    private JLabel profileSurnameLabel;
    private JLabel profileGenderLabel;
    private JLabel profileBirthLabel;
    private JLabel profilePhoneLabel;
    private JTextField profileEmailField;
    private JLabel profileEmailLabel;
    private JLabel pageLabel;
    private JLabel appNameLabel;

    public AppView(Connection connection, ProfileController profileController, BandController bandController) {

        this.setTitle("Music App Number One");
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);
//        this.setResizable(false);

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "HIGHLIGHTS");

        CardLayout cardLayout2 = (CardLayout) buttonsCardPanel.getLayout();
        cardLayout2.show(buttonsCardPanel, "UNLOGGEDIN");

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SIGNIN");
                pageLabel.setText("Sign In");
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SIGNUP");
                pageLabel.setText("Sign Up");

            }
        });
        highlightsSidebarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HIGHLIGHTS");
                pageLabel.setText("Highlights");
            }
        });
        artistsSidebarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ARTISTS");
                pageLabel.setText("Artists");

            }
        });
        bandsSidebarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "BANDS");
                pageLabel.setText("Bands");

            }
        });
        albumsSideBarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ALBUMS");
                pageLabel.setText("Albums");

            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            private void updateSearchResults() {
                String searchText = searchField.getText().trim().toLowerCase();
//                cardLayout.show(cardPanel, "SEARCHRESULTS");

                // Checking which tab is currently open, assuming you have some logic to track that
                if (pageLabel.getText().equalsIgnoreCase("Bands")) {
                    List<Band> bands = bandController.listBands();  // Assuming bandController exists

                    if (bands != null) {
                        for (Band band : bands) {
                            if (band.getName().toLowerCase().contains(searchText)) {
                                JPanel bandPanel = new JPanel(new GridLayout(4, 1));  // 4 rows: name, genre, description, country

                                JLabel nameLabel = new JLabel("Name: " + band.getName());
                                JLabel genreLabel = new JLabel("Genre: " + band.getGenre());
                                JLabel descriptionLabel = new JLabel("Description: " + band.getDescription());
                                JLabel countryLabel = new JLabel("Country: " + band.getCountry());

                                // Add labels to the bandPanel
                                bandPanel.add(nameLabel);
                                bandPanel.add(genreLabel);
                                bandPanel.add(descriptionLabel);
                                bandPanel.add(countryLabel);

                                // Add the bandPanel to the cardPanel
                                cardPanel.add(bandPanel);
                            }
                        }
                    } else {
                        System.out.println("Bands are null");
                        JTextField textNotFound = new JTextField();
                        textNotFound.setText("No results found");
                        cardPanel.add(textNotFound);
                    }

//                    bandsPanel.setText(matchingBands.toString());

                }
                // You can add similar logic for other categories like "Artists" or "Albums"
            }
        });

        // other action listeners like sidebar button

    // other methods
//        searchField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(cardPanel, "SEARCHRESULTS");
//                pageLabel.setText("Search results");
//
//            }
//        });


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginField.getText();
                String password = passwordField.getText();
                User user = Session.getInstance().getLoggedInUser();
                Profile profile = profileController.getProfile(username);

                if (user != null){
                    cardLayout2.show(buttonsCardPanel, "LOGGEDIN");
                    pageLabel.setText("Profile");
                    cardLayout.show(cardPanel, "PROFILE");
                }

                if (profile != null && profile.getPassword().equals(password)) {
                    User loggedInUser = new User(profile.getUsername(), profile.getPassword(), profile.getEmail(), profile.getRole());
                    Session.getInstance().setLoggedInUser(loggedInUser);
                    cardLayout2.show(buttonsCardPanel, "LOGGEDIN");
                    profileNameField.setText(profile.getName() != null ? profile.getName() : "No name");
                    profileSurnameField.setText(profile.getSurname() != null ? profile.getSurname() : "No surname");
                    profileGenderField.setText(profile.getGender() != null ? profile.getGender().toString() : "Gender unspecified");
                    profileBirthField.setText(profile.getDateOfBirth() != null ? profile.getDateOfBirth().toString() : "No date of birth");
                    profilePhoneField.setText(profile.getPhone() != null ? profile.getPhone() : "No phone number");
                    profileEmailField.setText(profile.getEmail() != null ? profile.getEmail() : "No email");
                    pageLabel.setText("Profile");
                    cardLayout.show(cardPanel, "PROFILE");
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session.getInstance().setLoggedInUser(null);
                cardLayout2.show(buttonsCardPanel, "LOGGEDOUT");
                pageLabel.setText("Highlights");
                cardLayout.show(cardPanel, "HIGHLIGHTS");
            }
        });
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "PROFILE");
                pageLabel.setText("Profile");
                Profile profile = profileController.getProfile(Session.getInstance().getLoggedInUser().getUsername());
                profileNameField.setText(profile.getName() != null ? profile.getName() : "No name");
                profileSurnameField.setText(profile.getSurname() != null ? profile.getSurname() : "No surname");
                profileGenderField.setText(profile.getGender() != null ? profile.getGender().toString() : "Gender unspecified");
                profileBirthField.setText(profile.getDateOfBirth() != null ? profile.getDateOfBirth().toString() : "No date of birth");
                profilePhoneField.setText(profile.getPhone() != null ? profile.getPhone() : "No phone number");
                profileEmailField.setText(profile.getEmail() != null ? profile.getEmail() : "No email");
            }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                searchField.setText("");
            }
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                searchField.setText("Search...");
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String createUserSQL = "INSERT INTO \"User\" (username, password, email) VALUES (?, ?, ?);";
                String createProfileSQL = "INSERT INTO Profile (name, surname, phone, gender) VALUES (?, ?, ?, ?);";
                try (PreparedStatement userStmt = connection.prepareStatement(createUserSQL)) {
                    userStmt.setString(1, usernameField.getText());
                    userStmt.setString(2, passwordField1.getText());
                    userStmt.setString(3, emailField.getText());
                    userStmt.executeUpdate();
                    try (PreparedStatement profileStmt = connection.prepareStatement(createProfileSQL)) {
                        profileStmt.setString(1, nameField.getText());
                        profileStmt.setString(2, surnameField.getText());
                        profileStmt.setString(3, phoneNumberField.getText());
                        profileStmt.setString(4, genderBox.getSelectedItem().toString());
                        profileStmt.executeUpdate();
                    }
                    pageLabel.setText("User created!");
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {
        artistIcon1 = new JLabel();
        artistIcon2 = new JLabel();
        artistImage4 = new JLabel();
        artistImage3 = new JLabel();
        albumLogo1 = new JLabel();
        albumLogo2 = new JLabel();
        albumLogo3 = new JLabel();
        albumLogo4 = new JLabel();
        bandImage1 = new JLabel();
        bandImage2 = new JLabel();
        createPrikol(artistIcon1, "/imgs/tehnik.jpg");
        createPrikol(artistIcon2, "/imgs/zemfira.jpg");
        createPrikol(artistImage4, "/imgs/drake.jpg");
        createPrikol(artistImage3, "/imgs/krug.jpg");
        createPrikol(albumLogo1, "/imgs/krugAlbum.png");
        createPrikol(albumLogo2, "/imgs/zemfiraAlbum.jpg");
        createPrikol(albumLogo3, "/imgs/drakeAlbum.jpg");
        createPrikol(albumLogo4, "/imgs/tehnikAlbum.jpg");
        createPrikol(bandImage1, "/imgs/gillianCarter.jpeg");
        createPrikol(bandImage2, "/imgs/nirvana.jpg");
    }

    private void createPrikol(JLabel label, String imageName){
        // Load the image from the classpath
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imageName));
        Image originalImage = originalIcon.getImage();

        // Define the size for the label (adjust to your needs)
        int labelWidth = 350;  // You can dynamically adjust this based on label size
        int labelHeight = 350;

        // Create a BufferedImage to draw the rounded corners
        BufferedImage roundedImage = new BufferedImage(labelWidth, labelHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        // Enable anti-aliasing for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define the rounded rectangle shape
        int arcWidth = 50;  // Adjust for the roundness of the corners
        int arcHeight = 50;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, labelWidth, labelHeight, arcWidth, arcHeight);

        // Clip the image to the rounded rectangle
        g2d.setClip(roundedRectangle);

        // Draw the original image onto the clipped area
        g2d.drawImage(originalImage, 0, 0, labelWidth, labelHeight, null);

        // Dispose the graphics object to free up resources
        g2d.dispose();

        // Set the rounded image as an icon for the label
        ImageIcon roundedIcon = new ImageIcon(roundedImage);
        label.setIcon(roundedIcon);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
//        label.setBackground(Color.WHITE);       // Set the background color
//        label.setOpaque(true);                  // Make the label opaque to show the background color
        label.setIconTextGap(5);
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
//    label.setFont(new Font("Times New Roman", Font.PLAIN, 20))
    }
}
