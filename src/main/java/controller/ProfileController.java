package controller;

import model.Profile;
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

    public Profile getProfile(int id) {
        try {
            return profileService.getProfile(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createProfile(Profile profile) {
        try {
            profileService.createProfile(profile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProfile(Profile profile) {
        try {
            profileService.updateProfile(profile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProfile(int id) {
        try {
            profileService.deleteProfile(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
