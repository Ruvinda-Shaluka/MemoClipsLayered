package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.BookingBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.BookingDAO;
import lk.ijse.memoclipsinlayered.db.DBConnection;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.entity.BookingEntity;

import java.sql.Connection;
import java.sql.ResultSet;
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
    public boolean placeBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getInstance().getConnection();
            String type = "";
            String photographer = "";
            String videographer = "";
            try {
                connection.setAutoCommit(false);
                ResultSet rstType = SQLUtil.executeQuery("SELECT bookingType FROM booking WHERE bookingId = ? DESC LIMIT 1", bookingDto.getBookingId());
                ResultSet availability = SQLUtil.executeQuery("SELECT availability FROM Photographer WHERE availability = 'available' ");
                ResultSet videoAvailability = SQLUtil.executeQuery("SELECT availability FROM videographer WHERE availability = 'available' ");
                while (rstType.next()){
                    type = rstType.getString("bookingType");
                }

                while (availability.next()){
                    photographer = availability.getString("availability");
                }
                while (videoAvailability.next()){
                    videographer = videoAvailability.getString("availability");
                }

                boolean isTypeUpdated = false;
                if (type.equalsIgnoreCase("Wedding")){

                    boolean updated = SQLUtil.executeUpdate("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);
                    if (updated) {
                        isTypeUpdated = SQLUtil.executeUpdate("Update videographer SET status = 'Booked' WHERE availability = ?", videographer);
                    }

                } else if (type.equalsIgnoreCase("Studio")){

                    isTypeUpdated = SQLUtil.executeUpdate("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);

                } else if (type.equalsIgnoreCase("Outdoor")) {

                    boolean updated = SQLUtil.executeUpdate("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);
                    if (updated) {
                        isTypeUpdated = SQLUtil.executeUpdate("Update videographer SET status = 'Booked' WHERE availability = ?", videographer);
                    }

                } else if (type.equalsIgnoreCase("Potrait")) {

                    isTypeUpdated = SQLUtil.executeUpdate("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);

                } else {

                    boolean updated = SQLUtil.executeUpdate("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);
                    if (updated) {
                        isTypeUpdated = SQLUtil.executeUpdate("Update videographer SET status = 'Booked' WHERE availability = ?", videographer);
                    }

                }

                if (isTypeUpdated) {
                    connection.commit();
                    return true;
                }
                connection.rollback();
                return false;
            } catch (Exception e) {
                // new Alert(Alert.AlertType.ERROR, "Error in connecting to database").show();
                return false;
            } finally {
                connection.setAutoCommit(true);
                return true;
            }
    }
}
