package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.PhotoSessionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PhotoSessionBO extends SuperBO {
    public ArrayList<PhotoSessionDto> getAllPhotoSession() throws SQLException, ClassNotFoundException;
    public boolean savePhotoSession(PhotoSessionDto photoSessionDto) throws SQLException, ClassNotFoundException;
    public boolean updatePhotoSession(PhotoSessionDto photoSessionDto) throws SQLException, ClassNotFoundException;
    public boolean existPhotoSession(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePhotoSession(String id) throws SQLException, ClassNotFoundException;
    public String generateNewPhotoSessionId() throws SQLException, ClassNotFoundException;
    public ArrayList<PhotoSessionDto> searchPhotoSession(String id) throws SQLException, ClassNotFoundException;
}
