package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotoStorageBO;
import lk.ijse.memoclipsinlayered.dto.PhotoStorageDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoStorageBOImpl implements PhotoStorageBO {
    @Override
    public ArrayList<PhotoStorageDto> getAllPhotoStorage() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePhotoStorage(PhotoStorageDto photoStorageDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePhotoStorage(PhotoStorageDto photoStorageDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existPhotoStorage(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletePhotoStorage(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewPhotoStorageId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<PhotoStorageDto> searchPhotoStorage(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
