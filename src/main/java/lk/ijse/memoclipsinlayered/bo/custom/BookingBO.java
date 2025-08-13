package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.AssistantDto;
import lk.ijse.memoclipsinlayered.dto.BookingDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBO {
    public ArrayList<BookingDto> getAllBooking() throws SQLException, ClassNotFoundException;
    public boolean saveBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException;
    public boolean updateBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException;
    public boolean existBooking(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteBooking(String id) throws SQLException, ClassNotFoundException;
    public String generateNewBookingId() throws SQLException, ClassNotFoundException;
    public ArrayList<BookingDto> searchBooking(String id) throws SQLException, ClassNotFoundException;
}
