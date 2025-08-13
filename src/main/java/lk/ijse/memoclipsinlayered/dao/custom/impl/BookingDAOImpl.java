package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.BookingDAO;
import lk.ijse.memoclipsinlayered.entity.BookingEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean save(BookingEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Booking (bookingId, customer_id, date, time, location, event_type, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                dto.getBookingId(),
                dto.getCustomerId(),
                dto.getDate(),
                dto.getTime(),
                dto.getLocation(),
                dto.getBookingType(),
                dto.getBookingStatus()
        );
    }

    @Override
    public boolean update(BookingEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Booking SET customer_id = ?, date = ?, time = ?, location = ?, event_type = ?, status = ? WHERE booking_id = ?",
                dto.getCustomerId(),
                dto.getDate(),
                dto.getTime(),
                dto.getLocation(),
                dto.getBookingType(),
                dto.getBookingStatus(),
                dto.getBookingId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "DELETE FROM booking WHERE booking_id = ?",
                id
        );
    }

    @Override
    public ArrayList<BookingEntity> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery(
                "SELECT * FROM booking WHERE booking_id = ?",
                id
        );
        ArrayList<BookingEntity> bookings = new ArrayList<>();

        if (rs.next()) {bookings.add(
            new BookingEntity(
                    rs.getString("booking_id"),
                    rs.getString("customer_id"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("event_type"),
                    rs.getString("status")
            ));
        }
        return bookings;
    }

    @Override
    public ArrayList<BookingEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM booking");
        ArrayList<BookingEntity> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new BookingEntity(
                    rs.getString("bookingId"),
                    rs.getString("customerId"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("bookingType"),
                    rs.getString("bookingStatus")
            ));
        }

        return list;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT bookingId FROM booking ORDER BY bookingId DESC LIMIT 1");
        String prefix = "BOOK";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numberPart);
            String newId = String.format(prefix + "%03d", number + 1);
            return newId;
        }
        return prefix + "001";
    }

/*    public ArrayList<String> getAllBookingIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT bookingId FROM booking");
        ArrayList<String> ids = new ArrayList<>();

        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }

    public ArrayList<BookingDto> getBookingsByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM booking WHERE customerId = ?", customerId);
        ArrayList<BookingDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new BookingDto(
                    rs.getString("bookingId"),
                    rs.getString("customerId"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("location"),
                    rs.getString("bookingType"),
                    rs.getString("bookingStatus")
            ));
        }
        return list;
    }

    public boolean placeBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String type = "";
        String photographer = "";
        String videographer = "";
        try {
            connection.setAutoCommit(false);
            ResultSet rstType = CrudUtil.execute("SELECT bookingType FROM booking WHERE bookingId = ? DESC LIMIT 1", bookingDto.getBookingId());
            ResultSet availability = CrudUtil.execute("SELECT availability FROM Photographer WHERE availability = 'available' ");
            ResultSet videoAvailability = CrudUtil.execute("SELECT availability FROM videographer WHERE availability = 'available' ");
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

                boolean updated = CrudUtil.execute("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);
                if (updated) {
                    isTypeUpdated = CrudUtil.execute("Update videographer SET status = 'Booked' WHERE availability = ?", videographer);
                }

            } else if (type.equalsIgnoreCase("Studio")){

                isTypeUpdated = CrudUtil.execute("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);

            } else if (type.equalsIgnoreCase("Outdoor")) {

                boolean updated = CrudUtil.execute("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);
                if (updated) {
                    isTypeUpdated = CrudUtil.execute("Update videographer SET status = 'Booked' WHERE availability = ?", videographer);
                }

            } else if (type.equalsIgnoreCase("Potrait")) {

                isTypeUpdated = CrudUtil.execute("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);

            } else {

                boolean updated = CrudUtil.execute("Update photographer SET status = 'Booked' WHERE availability = ?", photographer);
                if (updated) {
                    isTypeUpdated = CrudUtil.execute("Update videographer SET status = 'Booked' WHERE availability = ?", videographer);
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
    }*/
}
