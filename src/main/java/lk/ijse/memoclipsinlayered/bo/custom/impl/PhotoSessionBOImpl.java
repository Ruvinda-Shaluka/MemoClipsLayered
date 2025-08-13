package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotoSessionBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.PhotoSessionDAO;
import lk.ijse.memoclipsinlayered.dto.PhotoSessionDto;
import lk.ijse.memoclipsinlayered.entity.PhotoSessionEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoSessionBOImpl implements PhotoSessionBO {
    PhotoSessionDAO photoSessionDAO = (PhotoSessionDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.PHOTO_SESSION);
    @Override
    public ArrayList<PhotoSessionDto> getAllPhotoSession() throws SQLException, ClassNotFoundException {
        ArrayList<PhotoSessionEntity> entities = photoSessionDAO.getAll();
        ArrayList<PhotoSessionDto> photoSessionDtos = new ArrayList<>();
        for (PhotoSessionEntity entity : entities) {
            photoSessionDtos.add(new PhotoSessionDto(
                    entity.getSessionId(),
                    entity.getBookingId(),
                    entity.getPhotographerId(),
                    entity.getSessionType(),
                    entity.getDuration()
            ));
        }
        return photoSessionDtos;
    }

    @Override
    public boolean savePhotoSession(PhotoSessionDto photoSessionDto) throws SQLException, ClassNotFoundException {
        return photoSessionDAO.save(new PhotoSessionEntity(
                photoSessionDto.getSessionId(),
                photoSessionDto.getBookingId(),
                photoSessionDto.getPhotographerId(),
                photoSessionDto.getSessionType(),
                photoSessionDto.getDuration()
        ));
    }

    @Override
    public boolean updatePhotoSession(PhotoSessionDto photoSessionDto) throws SQLException, ClassNotFoundException {
        return photoSessionDAO.update(new PhotoSessionEntity(
                photoSessionDto.getSessionId(),
                photoSessionDto.getBookingId(),
                photoSessionDto.getPhotographerId(),
                photoSessionDto.getSessionType(),
                photoSessionDto.getDuration()
        ));
    }

    @Override
    public boolean existPhotoSession(String id) throws SQLException, ClassNotFoundException {
        return photoSessionDAO.exist(id);
    }

    @Override
    public boolean deletePhotoSession(String id) throws SQLException, ClassNotFoundException {
        return photoSessionDAO.delete(id);
    }

    @Override
    public String generateNewPhotoSessionId() throws SQLException, ClassNotFoundException {
        return photoSessionDAO.generateNewId();
    }

    @Override
    public ArrayList<PhotoSessionDto> searchPhotoSession(String id) throws SQLException, ClassNotFoundException {
        ArrayList<PhotoSessionEntity> entities = photoSessionDAO.search(id);
        ArrayList<PhotoSessionDto> photoSessionDtos = new ArrayList<>();
        for (PhotoSessionEntity entity : entities) {
            photoSessionDtos.add(new PhotoSessionDto(
                    entity.getSessionId(),
                    entity.getBookingId(),
                    entity.getPhotographerId(),
                    entity.getSessionType(),
                    entity.getDuration()
            ));
        }
        return photoSessionDtos;
    }
}
