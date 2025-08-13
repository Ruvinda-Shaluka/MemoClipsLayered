package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.AlbumDetailsDAO;
import lk.ijse.memoclipsinlayered.entity.AlbumDetailsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumDetailDAOImpl implements AlbumDetailsDAO {

    @Override
    public boolean save(AlbumDetailsEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Album_details (detailId, albumId, numberOfPhotos, coverType, size, paperQuantity) VALUES (?, ?, ?, ?, ?, ?)",
                dto.getDetailId(),
                dto.getAlbumId(),
                Integer.parseInt(dto.getNumberOfPhotos()),
                dto.getCoverType(),
                dto.getSize(),
                Integer.parseInt(dto.getPaperQuantity())
        );
    }

    @Override
    public boolean update(AlbumDetailsEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE album_details SET albumId = ?, numberOfPhotos = ?, coverType = ?, size = ?, paperQuantity = ? WHERE detailId = ?",
                dto.getAlbumId(),
                dto.getNumberOfPhotos(),
                dto.getCoverType(),
                dto.getSize(),
                dto.getPaperQuantity(),
                dto.getDetailId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "DELETE FROM Album_details WHERE detailId = ?",
                id
        );
    }

    @Override
    public ArrayList<AlbumDetailsEntity> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery(
                "SELECT * FROM Album_details WHERE detailId = ?",
                id
        );
        ArrayList<AlbumDetailsEntity> list = new ArrayList<>();

        if (rs.next()) {
            list.add(new AlbumDetailsEntity(
                    rs.getString("detailId"),
                    rs.getString("albumId"),
                    String.valueOf(rs.getInt("numberOfPhotos")),
                    rs.getString("coverType"),
                    rs.getString("size"),
                    String.valueOf(rs.getInt("paperQuantity"))
            ));
        }
        return list;
    }

    @Override
    public ArrayList<AlbumDetailsEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Album_details");
        ArrayList<AlbumDetailsEntity> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new AlbumDetailsEntity(
                    rs.getString("detailId"),
                    rs.getString("albumId"),
                    String.valueOf(rs.getInt("numberOfPhotos")),
                    rs.getString("coverType"),
                    rs.getString("size"),
                    String.valueOf(rs.getInt("paperQuantity"))
            ));
        }
        return list;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT detailId FROM Album_details ORDER BY detailId DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString("detailId");
            int number = Integer.parseInt(lastId.replaceAll("[^0-9]", ""));
            return String.format("D%03d", number + 1);
        }
        return "D001";
    }

/*    public ArrayList<String> getAllDetailIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT detailId FROM Album_details");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("detailId"));
        }
        return ids;
    }*/
}
