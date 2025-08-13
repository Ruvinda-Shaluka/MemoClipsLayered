package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.PhotoStorageDAO;
import lk.ijse.memoclipsinlayered.entity.PhotoStorageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoStorageDAOImpl implements PhotoStorageDAO {

    public boolean save(PhotoStorageEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO PhotoStorage (photoId, sessionId, uploadDate, uploadTime) VALUES (?, ?, ?, ?)",
                dto.getPhotoId(),
                dto.getSessionId(),
                dto.getUploadDate(),
                dto.getUploadTime()
        );
    }

    public boolean update(PhotoStorageEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE PhotoStorage SET sessionId = ?, uploadDate = ?, uploadTime = ? WHERE photoId = ?",
                dto.getSessionId(),
                dto.getUploadDate(),
                dto.getUploadTime(),
                dto.getPhotoId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String photoId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM PhotoStorage WHERE photoId = ?", photoId);
    }

    public ArrayList<PhotoStorageEntity> search(String photoId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM PhotoStorage WHERE photoId = ?", photoId);
        ArrayList<PhotoStorageEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
                new PhotoStorageEntity(
                    rs.getString("photoId"),
                    rs.getString("sessionId"),
                    rs.getString("uploadDate"),
                    rs.getString("uploadTime")
            ));
        }
        return list;
    }

    public ArrayList<PhotoStorageEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM PhotoStorage");
        ArrayList<PhotoStorageEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotoStorageEntity(
                    rs.getString("photoId"),
                    rs.getString("sessionId"),
                    rs.getString("uploadDate"),
                    rs.getString("uploadTime")
            ));
        }
        return list;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT photoId FROM PhotoStorage ORDER BY photoId DESC LIMIT 1");
        String prefix = "PHO";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(prefix.length()); // remove prefix
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

/*    public ArrayList<String> getAllPhotoIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT photoId FROM PhotoStorage");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("photoId"));
        }
        return list;
    }*/
}
