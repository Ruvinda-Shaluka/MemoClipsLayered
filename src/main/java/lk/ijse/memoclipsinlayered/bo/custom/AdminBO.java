package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminBO extends SuperBO {
    public ArrayList<AdminDto> getAllAdmin() throws SQLException, ClassNotFoundException;
    public boolean saveAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException;
    public boolean updateAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException;
    public boolean existAdmin(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteAdmin(String id) throws SQLException, ClassNotFoundException;
    public String generateNewAdminId() throws SQLException, ClassNotFoundException;
    public ArrayList<AdminDto> searchAdmin(String id) throws SQLException, ClassNotFoundException;

}
