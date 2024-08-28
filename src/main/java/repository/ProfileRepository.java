package repository;

import model.Gender;
import model.Profile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileRepository {

    private final Connection connection;

    public ProfileRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Profile profile) throws SQLException {
        String sql = "INSERT INTO profile (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, profile.getName());
//            stmt.setString(2, profile.getEmail());
//            stmt.executeUpdate();
        }
    }

    public Profile get(int id) throws SQLException {
        String sql = "SELECT u.username, u.password, u.email, p.phone, p.date_of_birth, p.gender " +
                "FROM \"User\" u " +
                "JOIN profile p ON u.id = p.id " +
                "WHERE u.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String genderString = rs.getString("gender");
                Gender gender = Gender.valueOf(genderString.toUpperCase());

                return new Profile(username, password, email, phone, dateOfBirth, gender);
            }
        }
        return null;
    }

    public List<Profile> list() throws SQLException {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT u.username, p.password, p.email, p.phone, p.date_of_birth, p.gender " +
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
                Gender gender = Gender.valueOf(genderString.toUpperCase());

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
