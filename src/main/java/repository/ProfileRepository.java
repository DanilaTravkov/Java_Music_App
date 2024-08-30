package repository;

import model.Gender;
import model.Profile;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {

    private final Connection connection;

    public ProfileRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(User user, Profile profile) throws SQLException {
        String insertUserSql = "INSERT INTO \"User\" (username, password, email) VALUES (?, ?);";
        String insertProfileSql = "INSERT INTO profile (id, name, surname, date_of_birth, phone, gender) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement userStmt = connection.prepareStatement(insertUserSql)) {
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getPassword());
            userStmt.setString(3, user.getEmail());

            ResultSet rs = userStmt.executeQuery();
            rs.next();
            int userId = rs.getInt(1);

            try (PreparedStatement profileStmt = connection.prepareStatement(insertProfileSql)) {
                profileStmt.setInt(1, userId);
                profileStmt.setString(2, profile.getName());
                profileStmt.setString(3, profile.getSurname());
                profileStmt.setDate(4, profile.getDateOfBirth());
                profileStmt.setString(5, profile.getPhone());
                profileStmt.setString(6, profile.getGender().toString());

                profileStmt.executeUpdate();
            }
        }
    }

    public void create(User user) throws SQLException {
        String insertUserSql = "INSERT INTO \"User\" (username, password, email) VALUES (?, ?, ?);";

        try (PreparedStatement userStmt = connection.prepareStatement(insertUserSql)) {
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getPassword());
            userStmt.setString(3, user.getEmail());

            userStmt.executeUpdate();
        }
    }

    public Profile get(String username) throws SQLException {
        String sql = "SELECT u.username, u.password, u.email, p.phone, p.date_of_birth, p.gender " +
                "FROM \"User\" u " +
                "JOIN profile p ON u.id = p.id " +
                "WHERE u.username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String usernameFromQuery = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String genderString = rs.getString("gender");
                Gender gender = null;
                if (genderString != null) {
                    try {
                        gender = Gender.valueOf(genderString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid gender value: " + genderString);
                    }
                }

                return new Profile(usernameFromQuery, password, email, phone, dateOfBirth, gender);
            }
        }
        return null;
    }

    public List<Profile> list() throws SQLException {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT u.username, u.password, u.email, p.phone, p.date_of_birth, p.gender " +
                "FROM \"User\" u " +
                "JOIN profile p ON u.id = p.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String genderString = rs.getString("gender");
                Gender gender = null;
                if (genderString != null) {
                    try {
                        gender = Gender.valueOf(genderString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid gender value: " + genderString);
                    }
                }

                Profile profile = new Profile(username, password, email, phone, dateOfBirth, gender);
                profiles.add(profile);
            }
        }
        return profiles;
    }


    public void update(Profile profile) throws SQLException {
        String sql = "UPDATE profile SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, profile.getName());
//            stmt.setString(2, profile.getEmail());
//            stmt.setInt(3, profile.getId());
//            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM profile WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
