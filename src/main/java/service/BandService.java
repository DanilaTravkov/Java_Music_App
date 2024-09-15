package service;

import model.Band;
import repository.BandRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BandService {
    private final BandRepository bandRepository;

    public BandService(Connection connection) {
        this.bandRepository = new BandRepository(connection);
    }

    public List<Band> listBands() throws SQLException {
        return bandRepository.list();
    }

    public Band getBand(int id) throws SQLException {
        return bandRepository.get(id);
    }

    public void createBand(Band band) throws SQLException {
        bandRepository.create(band);
    }

    public void updateBand(Band band, int id) throws SQLException {
        bandRepository.update(band, id);
    }

    public void deleteBand(int id) throws SQLException {
        bandRepository.delete(id);
    }
}
