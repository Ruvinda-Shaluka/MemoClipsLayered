package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotoSessionBO;
import lk.ijse.memoclipsinlayered.dto.PhotoSessionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoSessionBOImpl implements PhotoSessionBO {
    @Override
    public ArrayList<PhotoSessionDto> getAllPhotoSession() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePhotoSession(PhotoSessionDto photoSessionDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePhotoSession(PhotoSessionDto photoSessionDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existPhotoSession(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletePhotoSession(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewPhotoSessionId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<PhotoSessionDto> searchPhotoSession(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
