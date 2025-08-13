package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.AdminBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.AdminDAO;
import lk.ijse.memoclipsinlayered.dto.AdminDto;
import lk.ijse.memoclipsinlayered.entity.AdminEntity;
import lk.ijse.memoclipsinlayered.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public ArrayList<AdminDto> getAllAdmin() throws SQLException, ClassNotFoundException {
        ArrayList<AdminEntity> entity = adminDAO.getAll();
        ArrayList<AdminDto> admins = new ArrayList<>();
        for(AdminEntity e : entity){
            admins.add(new AdminDto(
                    e.getAdminId(),
                    e.getName(),
                    e.getPassword(),
                    e.getRole(),
                    e.getEmail()
                    ));
        }
        return admins;
    }

    @Override
    public boolean saveAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return adminDAO.save(new AdminEntity(
                adminDto.getAdminId(),
                adminDto.getName(),
                adminDto.getPassword(),
                adminDto.getRole(),
                adminDto.getEmail()
        ));
    }

    @Override
    public boolean updateAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return adminDAO.update(new AdminEntity(
                adminDto.getAdminId(),
                adminDto.getName(),
                adminDto.getPassword(),
                adminDto.getRole(),
                adminDto.getEmail()
        ));
    }

    @Override
    public boolean existAdmin(String id) throws SQLException, ClassNotFoundException {
        return adminDAO.exist(id);
    }

    @Override
    public boolean deleteAdmin(String id) throws SQLException, ClassNotFoundException {
        return adminDAO.delete(id);
    }

    @Override
    public String generateNewAdminId() throws SQLException, ClassNotFoundException {
        return adminDAO.generateNewId();
    }

    @Override
    public ArrayList<AdminDto> searchAdmin(String id) throws SQLException, ClassNotFoundException {
        ArrayList<AdminEntity> entity = adminDAO.search(id);
        ArrayList<AdminDto> admins = new ArrayList<>();
        for(AdminEntity e : entity){
            admins.add(new AdminDto(
                    e.getAdminId(),
                    e.getName(),
                    e.getPassword(),
                    e.getRole(),
                    e.getEmail()
            ));
        }
        return admins;
    }
}
