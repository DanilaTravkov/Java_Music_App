package repository;

import model.Album;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository implements CrudDTO<Album> {

    private Connection connection;

    public AlbumRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Album> list() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM Album";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Album album = new Album();
                album.setName(rs.getString("name"));
                album.setDate(rs.getDate("date"));
                album.setImgLink(rs.getString("imgLink"));
                albums.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albums;
    }

    @Override
    public Album get(int id) {
        Album album = null;
        String sql = "SELECT * FROM Album WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    album = new Album();
                    album.setName(rs.getString("name"));
                    album.setDate(rs.getDate("date"));
                    album.setImgLink(rs.getString("imgLink"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return album;
    }

    @Override
    public void create(Album newModel) {
        String sql = "INSERT INTO Album (name, date, imgLink) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newModel.getName());
            stmt.setDate(2, new java.sql.Date(newModel.getDate().getTime()));
            stmt.setString(3, newModel.getImgLink());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Album newModel, int id) {
        String sql = "UPDATE Album SET name = ?, date = ?, imgLink = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newModel.getName());
            stmt.setDate(2, new java.sql.Date(newModel.getDate().getTime()));
            stmt.setString(3, newModel.getImgLink());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Album WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
