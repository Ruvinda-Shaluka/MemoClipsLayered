package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.VideographerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VideographerBO extends SuperBO {
    public ArrayList<VideographerDto> getAllBooking() throws SQLException, ClassNotFoundException;
    public boolean saveBooking(VideographerDto videographerDto) throws SQLException, ClassNotFoundException;
    public boolean updateBooking(VideographerDto videographerDto) throws SQLException, ClassNotFoundException;
    public boolean existBooking(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteBooking(String id) throws SQLException, ClassNotFoundException;
    public String generateNewBookingId() throws SQLException, ClassNotFoundException;
    public ArrayList<VideographerDto> searchBooking(String id) throws SQLException, ClassNotFoundException;
}
