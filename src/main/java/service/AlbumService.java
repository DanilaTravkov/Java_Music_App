package service;

import model.Album;
import repository.AlbumRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(Connection connection) {
        this.albumRepository = new AlbumRepository(connection);
    }

    public List<Album> listAlbums() throws SQLException {
        return albumRepository.list();
    }

    public Album getAlbum(int id) throws SQLException {
        return albumRepository.get(id);
    }

    public void createAlbum(Album album) throws SQLException {
        albumRepository.create(album);
    }

    public void updateAlbum(Album album, int id) throws SQLException {
        albumRepository.update(album, id);
    }

    public void deleteAlbum(int id) throws SQLException {
        albumRepository.delete(id);
    }
}
