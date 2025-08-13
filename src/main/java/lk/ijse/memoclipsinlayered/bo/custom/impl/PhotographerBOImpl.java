package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotographerBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.PhotographerDAO;
import lk.ijse.memoclipsinlayered.dto.PhotographerDto;
import lk.ijse.memoclipsinlayered.entity.PhotographerEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotographerBOImpl implements PhotographerBO {
    PhotographerDAO photographerDAO = (PhotographerDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.PHOTOGRAPHER);
    @Override
    public ArrayList<PhotographerDto> getAllPhotographer() throws SQLException, ClassNotFoundException {
        ArrayList<PhotographerEntity> entities = photographerDAO.getAll();
        ArrayList<PhotographerDto> photographerDtos = new ArrayList<>();
        for (PhotographerEntity entity : entities) {
            photographerDtos.add(new PhotographerDto(
                    entity.getPhotographerId(),
                    entity.getName(),
                    entity.getSpeciality(),
                    entity.getContact(),
                    entity.getAvailability()
            ));
        }
        return photographerDtos;
    }

    @Override
    public boolean savePhotographer(PhotographerDto photographerDto) throws SQLException, ClassNotFoundException {
        return photographerDAO.save(new PhotographerEntity(
                photographerDto.getPhotographerId(),
                photographerDto.getName(),
                photographerDto.getSpeciality(),
                photographerDto.getContact(),
                photographerDto.getAvailability()
        ));
    }

    @Override
    public boolean updatePhotographer(PhotographerDto photographerDto) throws SQLException, ClassNotFoundException {
        return photographerDAO.update(new PhotographerEntity(
                photographerDto.getPhotographerId(),
                photographerDto.getName(),
                photographerDto.getSpeciality(),
                photographerDto.getContact(),
                photographerDto.getAvailability()
        ));
    }

    @Override
    public boolean existPhotographer(String id) throws SQLException, ClassNotFoundException {
        return photographerDAO.exist(id);
    }

    @Override
    public boolean deletePhotographer(String id) throws SQLException, ClassNotFoundException {
        return photographerDAO.delete(id);
    }

    @Override
    public String generateNewPhotographerId() throws SQLException, ClassNotFoundException {
        return photographerDAO.generateNewId();
    }

    @Override
    public ArrayList<PhotographerDto> searchPhotographer(String id) throws SQLException, ClassNotFoundException {
        ArrayList<PhotographerEntity> entities = photographerDAO.search(id);
        ArrayList<PhotographerDto> photographerDtos = new ArrayList<>();
        for (PhotographerEntity entity : entities) {
            photographerDtos.add(new PhotographerDto(
                    entity.getPhotographerId(),
                    entity.getName(),
                    entity.getSpeciality(),
                    entity.getContact(),
                    entity.getAvailability()
            ));
        }
        return photographerDtos;
    }
}
