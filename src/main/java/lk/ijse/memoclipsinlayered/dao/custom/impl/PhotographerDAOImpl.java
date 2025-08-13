package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.PhotographerDAO;
import lk.ijse.memoclipsinlayered.entity.PhotographerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotographerDAOImpl implements PhotographerDAO {

    public boolean save(PhotographerEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO photographer (photographerId, name, speciality, contact, availability) VALUES (?, ?, ?, ?, ?)",
                dto.getPhotographerId(),
                dto.getName(),
                dto.getSpeciality(),
                dto.getContact(),
                dto.getAvailability()
        );
    }

    public boolean update(PhotographerEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE photographer SET name = ?, speciality = ?, contact = ?, availability = ? WHERE photographerId = ?",
                dto.getName(),
                dto.getSpeciality(),
                dto.getContact(),
                dto.getAvailability(),
                dto.getPhotographerId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String photographerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM photographer WHERE photographerId = ?", photographerId);
    }

    public ArrayList<PhotographerEntity> search(String photographerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM photographer WHERE photographerId = ?", photographerId);
        ArrayList<PhotographerEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new PhotographerEntity(
                    rs.getString("photographerId"),
                    rs.getString("name"),
                    rs.getString("speciality"),
                    rs.getString("contact"),
                    rs.getString("availability")
            ));
        }
        return list;
    }

    public ArrayList<PhotographerEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM photographer");
        ArrayList<PhotographerEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PhotographerEntity(
                    rs.getString("photographerId"),
                    rs.getString("name"),
                    rs.getString("speciality"),
                    rs.getString("contact"),
                    rs.getString("availability")
            ));
        }
        return list;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT photographerId FROM photographer ORDER BY photographerId DESC LIMIT 1");
        String prefix = "PHO";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }

/*    public ArrayList<String> getAllPhotographerIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT photographerId FROM photographer");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("photographerId"));
        }
        return ids;
    }*/
}
