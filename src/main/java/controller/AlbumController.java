package controller;

import model.Album;
import service.AlbumService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(Connection connection) {
        this.albumService = new AlbumService(connection);
    }

    public List<Album> listAlbums() {
        try {
            return albumService.listAlbums();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Album getAlbum(int id) {
        try {
            return albumService.getAlbum(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAlbum(Album album) {
        try {
            albumService.createAlbum(album);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAlbum(Album album, int id) {
        try {
            albumService.updateAlbum(album, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAlbum(int id) {
        try {
            albumService.deleteAlbum(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
