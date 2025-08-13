package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.BookingBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.BookingDAO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.entity.BookingEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {

    BookingDAO bookingDAO = (BookingDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.BOOKING);
    @Override
    public ArrayList<BookingDto> getAllBooking() throws SQLException, ClassNotFoundException {
        ArrayList<BookingEntity> entity = bookingDAO.getAll();
        ArrayList<BookingDto> bookingDtos = new ArrayList<>();
        for (BookingEntity e : entity) {
            bookingDtos.add(new BookingDto(
                    e.getBookingId(),
                    e.getCustomerId(),
                    e.getDate(),
                    e.getTime(),
                    e.getLocation(),
                    e.getBookingType(),
                    e.getBookingStatus()

            ));
        }
        return bookingDtos;
    }

    @Override
    public boolean saveBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException {
        return bookingDAO.save(new BookingEntity(
                bookingDto.getBookingId(),
                bookingDto.getCustomerId(),
                bookingDto.getDate(),
                bookingDto.getTime(),
                bookingDto.getLocation(),
                bookingDto.getBookingType(),
                bookingDto.getBookingStatus()
        ));
    }

    @Override
    public boolean updateBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException {
        return bookingDAO.update(new BookingEntity(
                bookingDto.getBookingId(),
                bookingDto.getCustomerId(),
                bookingDto.getDate(),
                bookingDto.getTime(),
                bookingDto.getLocation(),
                bookingDto.getBookingType(),
                bookingDto.getBookingStatus()
        ));
    }

    @Override
    public boolean existBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.exist(id);
    }

    @Override
    public boolean deleteBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.delete(id);
    }

    @Override
    public String generateNewBookingId() throws SQLException, ClassNotFoundException {
        return bookingDAO.generateNewId();
    }

    @Override
    public ArrayList<BookingDto> searchBooking(String id) throws SQLException, ClassNotFoundException {
        ArrayList<BookingEntity> entity = bookingDAO.search(id);
        ArrayList<BookingDto> bookingDtos = new ArrayList<>();
        for (BookingEntity e : entity) {
            bookingDtos.add(new BookingDto(
                    e.getBookingId(),
                    e.getCustomerId(),
                    e.getDate(),
                    e.getTime(),
                    e.getLocation(),
                    e.getBookingType(),
                    e.getBookingStatus()
            ));
        }
        return bookingDtos;
    }

    @Override
    public boolean placeBooking(BookingDto bookingDto) {
        return false;
    }
}
