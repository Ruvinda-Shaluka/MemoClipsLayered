package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.VideographerBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.VideographerDAO;
import lk.ijse.memoclipsinlayered.dto.VideographerDto;
import lk.ijse.memoclipsinlayered.entity.VideographerEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class VideographerBOImpl implements VideographerBO {
    VideographerDAO videographerDAO = (VideographerDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.VIDEOGRAPHER);
    @Override
    public ArrayList<VideographerDto> getAllVideographer() throws SQLException, ClassNotFoundException {
        ArrayList<VideographerEntity> entities = videographerDAO.getAll();
        ArrayList<VideographerDto> videographerDtos = new ArrayList<>();
        for (VideographerEntity entity : entities) {
            videographerDtos.add(new VideographerDto(
            entity.getVideographerId(),
            entity.getName(),
            entity.getContact(),
            entity.getAvailability()
            ));
        }
        return videographerDtos;
    }

    @Override
    public boolean saveVideographer(VideographerDto videographerDto) throws SQLException, ClassNotFoundException {
        return videographerDAO.save(new VideographerEntity(
                videographerDto.getVideographerId(),
                videographerDto.getName(),
                videographerDto.getContact(),
                videographerDto.getAvailability()
        ));
    }

    @Override
    public boolean updateVideographer(VideographerDto videographerDto) throws SQLException, ClassNotFoundException {
        return videographerDAO.update(new VideographerEntity(
                videographerDto.getVideographerId(),
                videographerDto.getName(),
                videographerDto.getContact(),
                videographerDto.getAvailability()
        ));
    }

    @Override
    public boolean existVideographer(String id) throws SQLException, ClassNotFoundException {
        return videographerDAO.exist(id);
    }

    @Override
    public boolean deleteVideographer(String id) throws SQLException, ClassNotFoundException {
        return videographerDAO.delete(id);
    }

    @Override
    public String generateNewVideographerId() throws SQLException, ClassNotFoundException {
        return videographerDAO.generateNewId();
    }

    @Override
    public ArrayList<VideographerDto> searchVideographer(String id) throws SQLException, ClassNotFoundException {
        ArrayList<VideographerEntity> entities = videographerDAO.search(id);
        ArrayList<VideographerDto> videographerDtos = new ArrayList<>();
        for (VideographerEntity entity : entities) {
            videographerDtos.add(new VideographerDto(
                    entity.getVideographerId(),
                    entity.getName(),
                    entity.getContact(),
                    entity.getAvailability()
            ));
        }
        return videographerDtos;
    }
}
