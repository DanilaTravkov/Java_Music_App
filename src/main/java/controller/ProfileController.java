package controller;

import model.Profile;
import model.User;
import service.ProfileService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(Connection connection) {
        this.profileService = new ProfileService(connection);
    }

    public List<Profile> listProfiles() {
        try {
            return profileService.listProfiles();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of(); // Return an empty list on error
        }
    }

    public Profile getProfile(String username) {
        try {
            return profileService.getProfile(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createProfile(User user, Profile profile) {
        try {
            profileService.createProfile(user, profile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProfile(User user) {
        try {
            profileService.createProfile(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user, String username) {
        try {
            profileService.updateUser(user, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProfile(Profile profile, String username) {
        try {
            profileService.updateProfile(profile, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProfile(String username) {
        try {
            profileService.deleteProfile(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
