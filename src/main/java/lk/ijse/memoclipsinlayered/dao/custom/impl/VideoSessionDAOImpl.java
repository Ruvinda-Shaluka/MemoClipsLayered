package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.VideoSessionDAO;
import lk.ijse.memoclipsinlayered.entity.VideoSessionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoSessionDAOImpl implements VideoSessionDAO {

    public boolean save(VideoSessionEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO videoSessions (videoId, bookingId, videographerId, duration, format) VALUES (?, ?, ?, ?, ?)",
                dto.getVideoId(),
                dto.getBookingId(),
                dto.getVideographerId(),
                dto.getDuration(),
                dto.getFormat()
        );
    }

    public boolean update(VideoSessionEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE videoSessions SET bookingId = ?, videographerId = ?, duration = ?, format = ? WHERE videoId = ?",
                dto.getBookingId(),
                dto.getVideographerId(),
                dto.getDuration(),
                dto.getFormat(),
                dto.getVideoId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM videoSessions WHERE videoId = ?", id);
    }

    public ArrayList<VideoSessionEntity> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM videoSessions WHERE videoId = ?", id);
        ArrayList<VideoSessionEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new VideoSessionEntity(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }
        return list;
    }

    public ArrayList<VideoSessionEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM videosessions");
        ArrayList<VideoSessionEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new VideoSessionEntity(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }
        return list;
    }

    /*public String getNextVideoSessionId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT videoId FROM videoSessions ORDER BY videoId DESC LIMIT 1");
        String prefix = "VID";

        if (rs.next()) {
            String lastId = rs.getString("videoId");
            String numeric = lastId.substring(prefix.length());
            int number = Integer.parseInt(numeric);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }*/

    public String generateNewId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = SQLUtil.executeQuery("SELECT videoId FROM videosessions ORDER BY videoId DESC LIMIT 1");
        String  tableCharacter = "VIDS";

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(tableCharacter.length());
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableCharacter + "001";
    }

/*    public ArrayList<String> getAllVideoSessionIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT videoId FROM videoSessions");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("videoId"));
        }
        return ids;
    }*/
}
