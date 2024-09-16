package service;

import model.Artist;
import repository.ArtistRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(Connection connection) {
        this.artistRepository = new ArtistRepository(connection);
    }

    public List<Artist> listArtists() throws SQLException {
        return artistRepository.list();
    }

    public Artist getArtist(int id) throws SQLException {
        return artistRepository.get(id);
    }

    public void createArtist(Artist artist) throws SQLException {
        artistRepository.create(artist);
    }

    public void updateArtist(Artist artist, int id) throws SQLException {
        artistRepository.update(artist, id);
    }

    public void deleteArtist(int id) throws SQLException {
        artistRepository.delete(id);
    }
}
