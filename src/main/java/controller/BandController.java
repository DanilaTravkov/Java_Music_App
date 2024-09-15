package controller;

import model.Band;
import service.BandService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BandController {

    private final BandService bandService;

    public BandController(Connection connection) {
        this.bandService = new BandService(connection);
    }

    public List<Band> listBands() {
        try {
            return bandService.listBands();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public Band getBand(int id) {
        try {
            return bandService.getBand(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
