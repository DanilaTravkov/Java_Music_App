package view;

import controller.AlbumController;
import controller.ArtistController;
import controller.BandController;
import controller.ProfileController;
import model.*;
import session.Session;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
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
    private JPasswordField registerPasswordField;
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
    private JPanel albumPanel;
    private JPanel artistPanel;
    private JPanel bandPanel;
    private JLabel artistPageImage;
    private JLabel artistNicknameLabel;
    private JLabel artistNameSurnameDateLabel;
    private JLabel bandPageLogo;
    private JLabel bandPageNameLabel;
    private JLabel bandPageGenreLabel;
    private JLabel bandPageCountryLabel;
    private JLabel bandPageDatesLabel;
    private JLabel albumPageImage;
    private JLabel albumPageTitleLabel;
    private JLabel albumPageDateLabel;
    private JTextPane bandPageDescription;

    public AppView(Connection connection, ProfileController profileController, BandController bandController, AlbumController albumController, ArtistController artistController) {

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
                String createUserSQL = "INSERT INTO \"User\" (username, password, email, role) VALUES (?, ?, ?, ?);";
                String updateProfileSQL = "UPDATE Profile SET name = ?, surname = ?, phone = ?, gender = ? WHERE id = ?;";

                try (PreparedStatement userStmt = connection.prepareStatement(createUserSQL, Statement.RETURN_GENERATED_KEYS)) {
                    userStmt.setString(1, usernameField.getText());
                    userStmt.setString(2, new String(registerPasswordField.getPassword()));  // For password field
                    userStmt.setString(3, emailField.getText());
                    userStmt.setString(4, UserRoles.GENERAL.toString());

                    // Execute the user insert
                    userStmt.executeUpdate();

                    // Get the generated user ID
                    ResultSet generatedKeys = userStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1); // Assuming the ID is the first column in the result
                        System.out.println("Generated userId: " + userId); // Log the userId for debugging

                        // Now, update the Profile with user-provided data
                        try (PreparedStatement profileStmt = connection.prepareStatement(updateProfileSQL)) {
                            profileStmt.setString(1, nameField.getText());
                            profileStmt.setString(2, surnameField.getText());
                            profileStmt.setString(3, phoneNumberField.getText());
                            profileStmt.setString(4, genderBox.getSelectedItem().toString());
                            profileStmt.setInt(5, userId);  // Use the same userId to update Profile

                            // Execute the profile update
                            int profileUpdate = profileStmt.executeUpdate();
                            if (profileUpdate > 0) {
                                System.out.println("Profile updated for userId: " + userId); // Debugging log
                                pageLabel.setText("User and profile updated successfully!");
                            }
                        }
                    }
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        });
        albumLogo1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Album");
                Album album = albumController.getAlbum(3);
                setLabelIcon(albumPageImage, roundIcon("/imgs/krugAlbum.png", 350, 350, 50, 50));
                albumPageDateLabel.setText(album.getDate().toString());
                albumPageTitleLabel.setText(album.getName());
                cardLayout.show(cardPanel, "ALBUM");
            }
        });
        albumLogo2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Album");
                Album album = albumController.getAlbum(4);
                setLabelIcon(albumPageImage, roundIcon("/imgs/zemfiraAlbum.jpg", 350, 350, 50, 50));
                albumPageDateLabel.setText(album.getDate().toString());
                albumPageTitleLabel.setText(album.getName());
                cardLayout.show(cardPanel, "ALBUM");
            }
        });
        albumLogo3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Album");
                Album album = albumController.getAlbum(1);
                setLabelIcon(albumPageImage, roundIcon("/imgs/drakeAlbum.jpg", 350, 350, 50, 50));
                albumPageDateLabel.setText(album.getDate().toString());
                albumPageTitleLabel.setText(album.getName());
                cardLayout.show(cardPanel, "ALBUM");
            }
        });
        albumLogo4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Album");
                Album album = albumController.getAlbum(5);
                setLabelIcon(albumPageImage, roundIcon("/imgs/tehnikAlbum.jpg", 350, 350, 50, 50));
                albumPageDateLabel.setText(album.getDate().toString());
                albumPageTitleLabel.setText(album.getName());
                cardLayout.show(cardPanel, "ALBUM");

            }
        });
        artistIcon1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Artist");
                Artist artist = artistController.getArtist(2);
                setLabelIcon(artistPageImage, roundIcon("/imgs/tehnik.jpg", 350, 350, 350, 350));
                artistNicknameLabel.setText(artist.getNickname());
                artistNameSurnameDateLabel.setText(artist.getName() + " " + artist.getSurname() + ", " + artist.getDateOfBirth() + " - " + artist.getDateOfDeath());
                cardLayout.show(cardPanel, "ARTIST");
            }
        });
        artistIcon2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Artist");
                Artist artist = artistController.getArtist(3);
                setLabelIcon(artistPageImage, roundIcon("/imgs/zemfira.jpg", 350, 350, 350, 350));
                artistNicknameLabel.setText(artist.getNickname());
                artistNameSurnameDateLabel.setText(artist.getName() + " " + artist.getSurname() + ", " + artist.getDateOfBirth() + " - " + artist.getDateOfDeath());
                cardLayout.show(cardPanel, "ARTIST");
            }
        });
        artistImage3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Artist");
                Artist artist = artistController.getArtist(1);
                setLabelIcon(artistPageImage, roundIcon("/imgs/krug.jpg", 350, 350, 350, 350));
                artistNicknameLabel.setText(artist.getNickname());
                artistNameSurnameDateLabel.setText(artist.getName() + " " + artist.getSurname() + ", " + artist.getDateOfBirth() + " - " + artist.getDateOfDeath());
                cardLayout.show(cardPanel, "ARTIST");
            }
        });
        artistImage4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Artist");
                Artist artist = artistController.getArtist(4);
                setLabelIcon(artistPageImage, roundIcon("/imgs/drake.jpg", 350, 350, 350, 350));
                artistNicknameLabel.setText(artist.getNickname());
                artistNameSurnameDateLabel.setText(artist.getName() + " " + artist.getSurname() + ", " + artist.getDateOfBirth() + " - " + artist.getDateOfDeath());
                cardLayout.show(cardPanel, "ARTIST");
            }
        });
        bandImage1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Band");
                Band band = bandController.getBand(2);
                pageLabel.setText(band.getName());
                bandPageNameLabel.setText(band.getName());
                bandPageCountryLabel.setText(band.getCountry());
                bandPageDatesLabel.setText(band.getDateCreated().toString() + " - " + band.getDateDisbanded());
                bandPageDescription.setText(band.getDescription());
                bandPageGenreLabel.setText(band.getGenre().toString());
//                String imgPath = band.getImage();
                setLabelIcon(bandPageLogo, roundIcon("/imgs/gillianCarter.jpeg", 350, 350, 50, 50));
                cardLayout.show(cardPanel, "BAND");
            }
        });
        bandImage2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageLabel.setText("Band");
                Band band = bandController.getBand(1);
                pageLabel.setText(band.getName());
                bandPageNameLabel.setText(band.getName());
                bandPageCountryLabel.setText(band.getCountry());
                bandPageDatesLabel.setText(band.getDateCreated().toString() + " - " + band.getDateDisbanded());
                bandPageDescription.setText(band.getDescription());
                bandPageGenreLabel.setText(band.getGenre().toString());
//                String imgPath = band.getImage();
                setLabelIcon(bandPageLogo, roundIcon("/imgs/nirvana.jpg", 350, 350, 50, 50));
                cardLayout.show(cardPanel, "BAND");
            }
        });
    }

    private void createUIComponents() {
        artistIcon1 = new JLabel();     setLabelIcon(artistIcon1, roundIcon("/imgs/tehnik.jpg", 350, 350, 50, 50));
        artistIcon2 = new JLabel();     setLabelIcon(artistIcon2, roundIcon("/imgs/zemfira.jpg", 350, 350, 50, 50));
        artistImage4 = new JLabel();    setLabelIcon(artistImage4, roundIcon("/imgs/drake.jpg", 350, 350, 50, 50));
        artistImage3 = new JLabel();    setLabelIcon(artistImage3, roundIcon("/imgs/krug.jpg", 350, 350, 50, 50));
        albumLogo1 = new JLabel();      setLabelIcon(albumLogo1, roundIcon("/imgs/krugAlbum.png", 350, 350, 50, 50));
        albumLogo2 = new JLabel();      setLabelIcon(albumLogo2, roundIcon("/imgs/zemfiraAlbum.jpg", 350, 350, 50, 50));
        albumLogo3 = new JLabel();      setLabelIcon(albumLogo3, roundIcon("/imgs/drakeAlbum.jpg", 350, 350, 50, 50));
        albumLogo4 = new JLabel();      setLabelIcon(albumLogo4, roundIcon("/imgs/tehnikAlbum.jpg", 350, 350, 50, 50));
        bandImage1 = new JLabel();      setLabelIcon(bandImage1, roundIcon("/imgs/gillianCarter.jpeg", 350, 350, 50, 50));
        bandImage2 = new JLabel();      setLabelIcon(bandImage2, roundIcon("/imgs/nirvana.jpg", 350, 350, 50, 50));
        artistPageImage = new JLabel(); setLabelIcon(artistPageImage, roundIcon("/imgs/krug.jpg", 350, 350, 350, 350));
        bandPageLogo = new JLabel();    setLabelIcon(bandPageLogo, roundIcon("/imgs/nirvana.jpg", 350, 350, 350, 350));
        albumPageImage = new JLabel();  setLabelIcon(albumPageImage, roundIcon("/imgs/krugAlbum.png", 350, 350, 50, 50));

    }

    private void setLabelIcon(JLabel label, ImageIcon icon){
        label.setIcon(icon);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setIconTextGap(5);
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    private ImageIcon roundIcon(String imageName, int width, int height, int arcWidth, int arcHeight){
        // Load the image from the classpath
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imageName));
        Image originalImage = originalIcon.getImage();

        // Create a BufferedImage to draw the rounded corners
        BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        // Enable anti-aliasing for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, arcWidth, arcHeight);

        // Clip the image to the rounded rectangle
        g2d.setClip(roundedRectangle);

        // Draw the original image onto the clipped area
        g2d.drawImage(originalImage, 0, 0, width, height, null);

        // Dispose the graphics object to free up resources
        g2d.dispose();

        // Set the rounded image as an icon for the label
        ImageIcon roundedIcon = new ImageIcon(roundedImage);
        return roundedIcon;
    }
}
