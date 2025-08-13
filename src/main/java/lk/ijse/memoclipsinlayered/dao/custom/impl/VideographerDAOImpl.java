package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.VideographerDAO;
import lk.ijse.memoclipsinlayered.entity.VideographerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideographerDAOImpl implements VideographerDAO {
    @Override
    public boolean save(VideographerEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Videographer (videographerId, name, contact, availability) VALUES (?, ?, ?, ?)",
                dto.getVideographerId(),
                dto.getName(),
                dto.getContact(),
                dto.getAvailability()
        );
    }
    @Override
    public boolean update(VideographerEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Videographer SET name = ?, contact = ?, availability = ? WHERE videographerId = ?",
                dto.getName(),
                dto.getContact(),
                dto.getAvailability(),
                dto.getVideographerId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Videographer WHERE videographerId = ?", id);
    }
    @Override
    public ArrayList<VideographerEntity> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs =SQLUtil.executeQuery("SELECT * FROM Videographer WHERE videographerId = ?", id);
        ArrayList<VideographerEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new VideographerEntity(
                    rs.getString("videographerId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("availability")
            ));
        }
        return list;
    }
    @Override
    public ArrayList<VideographerEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Videographer");
        ArrayList<VideographerEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new VideographerEntity(
                    rs.getString("videographerId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("availability")
            ));
        }
        return list;
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT videographerId FROM Videographer ORDER BY videographerId DESC LIMIT 1");
        String prefix = "VID";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(prefix.length()); // Remove prefix
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

/*    public ArrayList<String> getAllVideographerIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT videographerId FROM Videographer");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("videographerId"));
        }
        return ids;
    }*/
}
