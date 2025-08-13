package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotographerBO;
import lk.ijse.memoclipsinlayered.dto.PhotographerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotographerBOImpl implements PhotographerBO {
    @Override
    public ArrayList<PhotographerDto> getAllPhotographer() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePhotographer(PhotographerDto photographerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePhotographer(PhotographerDto photographerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existPhotographer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletePhotographer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewPhotographerId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<PhotographerDto> searchPhotographer(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
