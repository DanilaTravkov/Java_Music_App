package repository;

import model.Gender;
import model.Profile;
import model.User;
import model.UserRoles;

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
        String insertUserSql = "INSERT INTO \"User\" (username, password, email, role) VALUES (?, ?, ?, ?);";

        try (PreparedStatement userStmt = connection.prepareStatement(insertUserSql)) {
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getPassword());
            userStmt.setString(3, user.getEmail());
            userStmt.setString(4, UserRoles.GENERAL.name());

            userStmt.executeUpdate();
        }
    }

    public Profile get(String username) throws SQLException {
        String sql = "SELECT u.username, u.password, u.email, u.role, p.phone, p.date_of_birth, p.gender " +
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
                String roleString = rs.getString("role");
                Gender gender = null;
                UserRoles role = null;
                if (genderString != null) {
                    try {
                        gender = Gender.valueOf(genderString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid gender value: " + genderString);
                    }
                }
                if (roleString != null) {
                    try {
                        role = UserRoles.valueOf(roleString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid role value: " + roleString);
                    }
                }

                return new Profile(usernameFromQuery, password, email, role, phone, dateOfBirth, gender);
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
                String roleString = rs.getString("role");
                Gender gender = null;
                UserRoles role = null;
                if (genderString != null) {
                    try {
                        gender = Gender.valueOf(genderString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid gender value: " + genderString);
                    }
                }
                if (roleString != null) {
                    try {
                        role = UserRoles.valueOf(roleString.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid role value: " + roleString);
                    }
                }


                Profile profile = new Profile(username, password, email, role, phone, dateOfBirth, gender);
                profiles.add(profile);
            }
        }
        return profiles;
    }


    public void updateUser(User user, String username) throws SQLException {
        String sql = "UPDATE \"User\" SET username = ?, email = ? WHERE username = ? RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, username);
            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                int userId = rs.getInt(1);
//                // You might want to use this ID for further operations or validation
//            }
        }
    }

    public void updateProfile(Profile profile, String username) throws SQLException {
        String sql = "UPDATE profile SET name = ?, phone = ?, date_of_birth = ?, gender = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, profile.getName());
            stmt.setString(2, profile.getPhone());
            stmt.setDate(3, profile.getDateOfBirth());
            stmt.setString(4, profile.getGender() != null ? profile.getGender().toString() : null);
            stmt.setString(5, profile.getUsername());
            stmt.executeUpdate();
        }
    }


    public void delete(String username) throws SQLException {
        String getUserIdSql = "SELECT id FROM \"User\" WHERE username = ?";
        int userId = -1;

        try (PreparedStatement getIdStmt = connection.prepareStatement(getUserIdSql)) {
            getIdStmt.setString(1, username);
            ResultSet rs = getIdStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            }
        }

        if (userId != -1) {
            // First delete from profile table
            String deleteProfileSql = "DELETE FROM profile WHERE id = ?";
            try (PreparedStatement deleteProfileStmt = connection.prepareStatement(deleteProfileSql)) {
                deleteProfileStmt.setInt(1, userId);
                deleteProfileStmt.executeUpdate();
            }

            // Then delete from User table
            String deleteUserSql = "DELETE FROM \"User\" WHERE username = ?";
            try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserSql)) {
                deleteUserStmt.setString(1, username);
                deleteUserStmt.executeUpdate();
            }
        }
    }

}
