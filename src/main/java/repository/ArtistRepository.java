package repository;

import model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository implements CrudDTO<Artist> {
    private final Connection connection;

    // Constructor to initialize the repository with a database connection
    public ArtistRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Artist> list() {
        String query = "SELECT * FROM Artist;";
        List<Artist> artists = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setNickname(rs.getString("nickname"));
                artist.setName(rs.getString("name"));
                artist.setSurname(rs.getString("surname"));
                artist.setDateOfBirth(rs.getDate("date_of_birth"));
                artist.setDateOfDeath(rs.getDate("date_of_death"));
                artist.setImgLink(rs.getString("img_link"));
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public Artist get(int id) {
        String query = "SELECT * FROM Artist WHERE id = ?;";
        Artist artist = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                artist = new Artist();
                artist.setNickname(rs.getString("nickname"));
                artist.setName(rs.getString("name"));
                artist.setSurname(rs.getString("surname"));
                artist.setDateOfBirth(rs.getDate("date_of_birth"));
                artist.setDateOfDeath(rs.getDate("date_of_death"));
                artist.setImgLink(rs.getString("img_link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artist;
    }

    @Override
    public void create(Artist artist) {
        String query = "INSERT INTO Artist (nickname, name, surname, date_of_birth, date_of_death, img_link) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, artist.getNickname());
            stmt.setString(2, artist.getName());
            stmt.setString(3, artist.getSurname());
            stmt.setDate(4, new java.sql.Date(artist.getDateOfBirth().getTime()));
            stmt.setDate(5, artist.getDateOfDeath() != null ? new java.sql.Date(artist.getDateOfDeath().getTime()) : null);
            stmt.setString(6, artist.getImgLink());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Artist artist, int id) {
        String query = "UPDATE Artist SET nickname = ?, name = ?, surname = ?, date_of_birth = ?, date_of_death = ?, img_link = ? WHERE id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, artist.getNickname());
            stmt.setString(2, artist.getName());
            stmt.setString(3, artist.getSurname());
            stmt.setDate(4, new java.sql.Date(artist.getDateOfBirth().getTime()));
            stmt.setDate(5, artist.getDateOfDeath() != null ? new java.sql.Date(artist.getDateOfDeath().getTime()) : null);
            stmt.setString(6, artist.getImgLink());
            stmt.setInt(7, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Artist WHERE id = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
