package controller;

import model.Artist;
import service.ArtistService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(Connection connection) {
        this.artistService = new ArtistService(connection);
    }

    public List<Artist> listArtists() {
        try {
            return artistService.listArtists();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Artist getArtist(int id) {
        try {
            return artistService.getArtist(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createArtist(Artist artist) {
        try {
            artistService.createArtist(artist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateArtist(Artist artist, int id) {
        try {
            artistService.updateArtist(artist, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteArtist(int id) {
        try {
            artistService.deleteArtist(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
