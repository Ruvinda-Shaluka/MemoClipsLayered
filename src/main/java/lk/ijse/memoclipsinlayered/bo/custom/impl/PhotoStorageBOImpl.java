package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotoStorageBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.PhotoStorageDAO;
import lk.ijse.memoclipsinlayered.dto.PhotoStorageDto;
import lk.ijse.memoclipsinlayered.entity.PhotoStorageEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoStorageBOImpl implements PhotoStorageBO {
    PhotoStorageDAO photoStorageDAO = (PhotoStorageDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.PHOTO_STORAGE);
    @Override
    public ArrayList<PhotoStorageDto> getAllPhotoStorage() throws SQLException, ClassNotFoundException {
        ArrayList<PhotoStorageEntity> entities = photoStorageDAO.getAll();
        ArrayList<PhotoStorageDto> photoStorageDtos = new ArrayList<>();
        for (PhotoStorageEntity entity : entities) {
            photoStorageDtos.add(new PhotoStorageDto(
                    entity.getPhotoId(),
                    entity.getSessionId(),
                    entity.getUploadDate(),
                    entity.getUploadTime()
            ));
        }
        return photoStorageDtos;
    }

    @Override
    public boolean savePhotoStorage(PhotoStorageDto photoStorageDto) throws SQLException, ClassNotFoundException {
        return photoStorageDAO.save(new PhotoStorageEntity(
                photoStorageDto.getPhotoId(),
                photoStorageDto.getSessionId(),
                photoStorageDto.getUploadDate(),
                photoStorageDto.getUploadTime()
        ));
    }

    @Override
    public boolean updatePhotoStorage(PhotoStorageDto photoStorageDto) throws SQLException, ClassNotFoundException {
        return photoStorageDAO.update(new PhotoStorageEntity(
                photoStorageDto.getPhotoId(),
                photoStorageDto.getSessionId(),
                photoStorageDto.getUploadDate(),
                photoStorageDto.getUploadTime()
        ));
    }

    @Override
    public boolean existPhotoStorage(String id) throws SQLException, ClassNotFoundException {
        return photoStorageDAO.exist(id);
    }

    @Override
    public boolean deletePhotoStorage(String id) throws SQLException, ClassNotFoundException {
        return photoStorageDAO.delete(id);
    }

    @Override
    public String generateNewPhotoStorageId() throws SQLException, ClassNotFoundException {
        return photoStorageDAO.generateNewId();
    }

    @Override
    public ArrayList<PhotoStorageDto> searchPhotoStorage(String id) throws SQLException, ClassNotFoundException {
        ArrayList<PhotoStorageEntity> entities = photoStorageDAO.search(id);
        ArrayList<PhotoStorageDto> photoStorageDtos = new ArrayList<>();
        for (PhotoStorageEntity entity : entities) {
            photoStorageDtos.add(new PhotoStorageDto(
                    entity.getPhotoId(),
                    entity.getSessionId(),
                    entity.getUploadDate(),
                    entity.getUploadTime()
            ));
        }
        return photoStorageDtos;
    }
}
