package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.PhotoSessionDAO;
import lk.ijse.memoclipsinlayered.entity.PhotoSessionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoSessionDAOImpl implements PhotoSessionDAO {

    public boolean save(PhotoSessionEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Photo_session (sessionId, bookingId, photographerId, sessionType, duration) VALUES (?, ?, ?, ?, ?)",
                dto.getSessionId(),
                dto.getBookingId(),
                dto.getPhotographerId(),
                dto.getSessionType(),
                dto.getDuration()
        );
    }

    public boolean update(PhotoSessionEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Photo_session SET bookingId = ?, photographerId = ?, sessionType = ?, duration = ? WHERE sessionId = ?",
                dto.getBookingId(),
                dto.getPhotographerId(),
                dto.getSessionType(),
                dto.getDuration(),
                dto.getSessionId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String sessionId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Photo_session WHERE sessionId = ?", sessionId);
    }

    public ArrayList<PhotoSessionEntity> search(String sessionId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Photo_session WHERE sessionId = ?", sessionId);
        ArrayList<PhotoSessionEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new PhotoSessionEntity(
                    rs.getString("sessionId"),
                    rs.getString("bookingId"),
                    rs.getString("photographerId"),
                    rs.getString("sessionType"),
                    rs.getString("duration")
            ));
        }
        return list;
    }

    public ArrayList<PhotoSessionEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Photo_session");
        ArrayList<PhotoSessionEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotoSessionEntity(
                    rs.getString("sessionId"),
                    rs.getString("bookingId"),
                    rs.getString("photographerId"),
                    rs.getString("sessionType"),
                    rs.getString("duration")
            ));
        }
        return list;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT sessionId FROM Photo_session ORDER BY sessionId DESC LIMIT 1");
        String prefix = "PHS";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

/*    public ArrayList<String> getAllSessionIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT sessionId FROM Photo_session");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("sessionId"));
        }
        return ids;
    }*/
}
