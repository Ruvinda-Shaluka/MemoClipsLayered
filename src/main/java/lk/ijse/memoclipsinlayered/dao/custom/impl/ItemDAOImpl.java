package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.ItemDAO;
import lk.ijse.memoclipsinlayered.entity.ItemEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    public boolean save(ItemEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO item (itemId, name, quantity, lastUpdateDate, supplierId) VALUES (?, ?, ?, ?, ?)",
                dto.getItemId(),
                dto.getItemName(),
                dto.getQuantity(),
                dto.getLastUpdateDate(),
                dto.getSupplierId()
        );
    }

    public boolean update(ItemEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE item SET name = ?, quantity = ?, lastUpdateDate = ?, supplierId = ? WHERE itemId = ?",
                dto.getItemName(),
                dto.getQuantity(),
                dto.getLastUpdateDate(),
                dto.getSupplierId(),
                dto.getItemId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM item WHERE itemId = ?", itemId);
    }

    public ArrayList<ItemEntity> search(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM item WHERE item_id = ?", itemId);
        ArrayList<ItemEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new ItemEntity(
                    rs.getString("item_id"),
                    rs.getString("name"),
                    rs.getString("quantity"),
                    rs.getString("last_updated_date"),
                    rs.getString("supplier_id")
            ));
        }
        return list;
    }

    public ArrayList<ItemEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM item");
        ArrayList<ItemEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new ItemEntity(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }
        return list;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1");
        String prefix = "ITM";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }
/*
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT item_id FROM item");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }*/
}
