package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.SupplierDAO;
import lk.ijse.memoclipsinlayered.entity.SupplierEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    public boolean save(SupplierEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Supplier (supplierId, name, contact, supplyQuantity) VALUES (?, ?, ?, ?)",
                dto.getSupplierId(),
                dto.getName(),
                dto.getContact(),
                dto.getSupplyQuantity()
        );
    }

    public boolean update(SupplierEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Supplier SET name = ?, contact = ?, supplyQuantity = ? WHERE supplierId = ?",
                dto.getName(),
                dto.getContact(),
                dto.getSupplyQuantity(),
                dto.getSupplierId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Supplier WHERE supplierId = ?", supplierId);
    }

    public ArrayList<SupplierEntity> search(String supplierId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Supplier WHERE supplierId = ?", supplierId);
        ArrayList<SupplierEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new SupplierEntity(
                    rs.getString("supplierId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("supplyQuantity")
            ));
        }
        return list;
    }

    public ArrayList<SupplierEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Supplier");
        ArrayList<SupplierEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new SupplierEntity(
                    rs.getString("supplierId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("supplyQuantity")
            ));
        }
        return list;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT supplierId FROM Supplier ORDER BY supplierId DESC LIMIT 1");
        String prefix = "SUP";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numericPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numericPart);
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }

/*    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT supplierId FROM Supplier");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("supplierId"));
        }
        return list;
    }*/
}
