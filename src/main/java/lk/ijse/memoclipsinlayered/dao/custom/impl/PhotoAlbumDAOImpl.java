package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.PhotoAlbumDAO;
import lk.ijse.memoclipsinlayered.entity.PhotoAlbumEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoAlbumDAOImpl implements PhotoAlbumDAO {
    @Override
    public boolean save(PhotoAlbumEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Photo_album (albumId, bookingId, albumType, price) VALUES (?, ?, ?, ?)",
                dto.getAlbumId(),
                dto.getBookingId(),
                dto.getAlbumType(),
                dto.getPrice()
        );
    }
    @Override
    public boolean update(PhotoAlbumEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Photo_album SET bookingId = ?, albumType = ?, price = ? WHERE albumId = ?",
                dto.getBookingId(),
                dto.getAlbumType(),
                dto.getPrice(),
                dto.getAlbumId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean delete(String albumId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Photo_album WHERE albumId = ?", albumId);
    }
    @Override
    public ArrayList<PhotoAlbumEntity> search(String albumId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Photo_album WHERE albumId = ?", albumId);
        ArrayList<PhotoAlbumEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new PhotoAlbumEntity(
                    rs.getString("albumId"),
                    rs.getString("bookingId"),
                    rs.getString("albumType"),
                    rs.getString("price")
            ));
        }
        return list;
    }
    @Override
    public ArrayList<PhotoAlbumEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Photo_album");
        ArrayList<PhotoAlbumEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotoAlbumEntity(
                    rs.getString("albumId"),
                    rs.getString("bookingId"),
                    rs.getString("albumType"),
                    rs.getString("price")
            ));
        }
        return list;
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT albumId FROM Photo_album ORDER BY albumId DESC LIMIT 1");
        String prefix = "ALB";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

/*    public ArrayList<String> getAllAlbumIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT albumId FROM Photo_album");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("albumId"));
        }
        return ids;
    }*/
}
