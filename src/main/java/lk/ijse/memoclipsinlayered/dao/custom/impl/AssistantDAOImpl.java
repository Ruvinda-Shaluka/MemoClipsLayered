package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.AssistantDAO;
import lk.ijse.memoclipsinlayered.entity.AssistantEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssistantDAOImpl implements AssistantDAO {
    @Override
    public boolean save(AssistantEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Assistant (assistantId, photographerId, name, availability) VALUES (?, ?, ?, ?)",
                dto.getAssistantId(),
                dto.getPhotographerId(),
                dto.getAssistantName(),
                dto.getAvailability()
        );
    }
    @Override
    public boolean update(AssistantEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Assistant SET photographerId = ?, name = ?, availability = ? WHERE assistantId = ?",
                dto.getPhotographerId(),
                dto.getAssistantName(),
                dto.getAvailability(),
                dto.getAssistantId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "DELETE FROM Assistant WHERE assistantId = ?",
                id
        );
    }
    @Override
    public ArrayList<AssistantEntity> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery(
                "SELECT * FROM Assistant WHERE assistantId = ?",
                id
        );
        ArrayList<AssistantEntity> list = new ArrayList<>();

        if (rs.next()) {list.add(
                new AssistantEntity(
                        rs.getString("assistantId"),
                        rs.getString("name"),
                        rs.getString("photographerId"),
                        rs.getString("availability")
                ));
        }
        return list;
    }
    @Override
    public ArrayList<AssistantEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Assistant");
        ArrayList<AssistantEntity> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new AssistantEntity(
                    rs.getString("assistantId"),
                    rs.getString("name"),
                    rs.getString("photographerId"),
                    rs.getString("availability")
            ));
        }

        return list;
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT assistantId FROM Assistant ORDER BY assistantId DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int number = Integer.parseInt(lastId.replaceAll("[^0-9]", ""));
            return String.format("A%03d", number + 1);
        }
        return "A001";
    }
/*
    public ArrayList<String> getAllAssistantIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT assistantId FROM Assistant");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }*/
}
