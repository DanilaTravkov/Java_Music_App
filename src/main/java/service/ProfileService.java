package service;

import model.Profile;
import repository.ProfileRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(Connection connection) {
        this.profileRepository = new ProfileRepository(connection);
    }

    public List<Profile> listProfiles() throws SQLException {
        return profileRepository.list();
    }

    public Profile getProfile(int id) throws SQLException {
        return profileRepository.get(id);
    }

    public void createProfile(Profile profile) throws SQLException {
        profileRepository.create(profile);
    }

    public void updateProfile(Profile profile) throws SQLException {
        profileRepository.update(profile);
    }

    public void deleteProfile(int id) throws SQLException {
        profileRepository.delete(id);
    }
}
