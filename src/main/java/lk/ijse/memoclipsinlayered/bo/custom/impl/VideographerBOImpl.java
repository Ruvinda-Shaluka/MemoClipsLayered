package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.VideographerBO;
import lk.ijse.memoclipsinlayered.dto.VideographerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class VideographerBOImpl implements VideographerBO {
    @Override
    public ArrayList<VideographerDto> getAllBooking() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveBooking(VideographerDto videographerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateBooking(VideographerDto videographerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existBooking(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteBooking(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewBookingId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<VideographerDto> searchBooking(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
