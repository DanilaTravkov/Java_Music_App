package repository;

import model.Band;
import model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BandRepository implements CrudDTO<Band> {

    private final Connection connection;

    public BandRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Band> list() {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM Band";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Band band = new Band();
                band.setName(rs.getString("name"));
                band.setGenre(Genre.valueOf(rs.getString("genre"))); // Assuming Genre is an Enum
                band.setDescription(rs.getString("description"));
                band.setCountry(rs.getString("country"));
                band.setDateCreated(rs.getDate("date_created"));
                band.setDateDisbanded(rs.getDate("date_disbanded"));
                bands.add(band);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bands;
    }

    @Override
    public Band get(int id) {
        Band band = null;
        String sql = "SELECT * FROM Band WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    band = new Band();
                    band.setName(rs.getString("name"));
                    band.setGenre(Genre.valueOf(rs.getString("genre")));
                    band.setDescription(rs.getString("description"));
                    band.setCountry(rs.getString("country"));
                    band.setDateCreated(rs.getDate("date_created"));
                    band.setDateDisbanded(rs.getDate("date_disbanded"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return band;
    }

    @Override
    public void create(Band band) {
        String sql = "INSERT INTO Band (name, genre, description, country, date_created, date_disbanded) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, band.getName());
            stmt.setString(2, band.getGenre().name()); // Assuming Genre is an Enum
            stmt.setString(3, band.getDescription());
            stmt.setString(4, band.getCountry());
            stmt.setDate(5, new java.sql.Date(band.getDateCreated().getTime()));
            stmt.setDate(6, band.getDateDisbanded() != null ? new java.sql.Date(band.getDateDisbanded().getTime()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Band band, int id) {
        String sql = "UPDATE Band SET name = ?, genre = ?, description = ?, country = ?, date_created = ?, date_disbanded = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, band.getName());
            stmt.setString(2, band.getGenre().name()); // Assuming Genre is an Enum
            stmt.setString(3, band.getDescription());
            stmt.setString(4, band.getCountry());
            stmt.setDate(5, new java.sql.Date(band.getDateCreated().getTime()));
            stmt.setDate(6, band.getDateDisbanded() != null ? new java.sql.Date(band.getDateDisbanded().getTime()) : null);
            stmt.setInt(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Band WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
