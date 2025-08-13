package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.AdminDAO;
import lk.ijse.memoclipsinlayered.dto.AdminDto;
import lk.ijse.memoclipsinlayered.entity.AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean save(AdminEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO admin VALUES (?, ?, ?, ? , ? )",
                dto.getAdminId(),
                dto.getName(),
                dto.getPassword(),
                dto.getRole(),
                dto.getEmail()
        );
    }

    @Override
    public boolean update(AdminEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE admin SET name = ?, password = ?, role = ?, email = ? WHERE adminId = ?",
                dto.getName(),
                dto.getPassword(),
                dto.getRole(),
                dto.getEmail(),
                dto.getAdminId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM admin WHERE adminId = ?", id);
    }

    @Override
    public ArrayList<AdminEntity> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM admin WHERE adminId = ?", id);
        ArrayList<AdminEntity> list = new ArrayList<>();
        if (rs.next()) {
            list.add(new AdminEntity(
                    rs.getString("adminId"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("email")
            ));
        }
        return list;
    }

    @Override
    public ArrayList<AdminEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM admin");
        ArrayList<AdminEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new AdminEntity(
                    rs.getString("adminId"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("email")
            ));
        }
        return list;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM admin WHERE adminId = ?", id);
        return resultSet.next();
    }

   /* public String getNextAdminId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT admin FROM Admin ORDER BY adminId DESC LIMIT 1");
        String prefix = "ADM";  // assuming USR001 format
        if (rs.next()) {
            String lastId = rs.getString(1);
            String lastIdNumString = lastId.substring(lastId.length());
            int num = Integer.parseInt(lastId.substring(lastIdNumString);  // skip "USR"
            int nextId = num + 1;
            String nextIdNumString = String.format()
            return String.format("USR%03d", nextId);
        }
        return "USR001";
    }*/

    @Override
    public String generateNewId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = SQLUtil.executeQuery("SELECT adminId FROM admin ORDER BY adminId DESC LIMIT 1");
        String tableString = "AMD";

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(tableString.length());
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableString + "001";
    }

/*    public ArrayList<String> getAllAdminIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT admin_id FROM Admin");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("admin_id"));
        }
        return ids;
    }

    public String findAdminNameById(String adminId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT name FROM admin WHERE adminId = ?", adminId);
        if (rs.next()) {
            return rs.getString("name");
        }
        return null;
    }

    public boolean checkAdminLogin(String username, String password) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery(
                "SELECT * FROM admin WHERE name = ? AND password = ?",
                username,
                password
        );
        return rs.next();  // returns true if user exists
    }*/
}
