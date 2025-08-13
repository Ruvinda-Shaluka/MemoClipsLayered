package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.PhotoStorageDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PhotoStorageBO extends SuperBO {
    public ArrayList<PhotoStorageDto> getAllPhotoStorage() throws SQLException, ClassNotFoundException;
    public boolean savePhotoStorage(PhotoStorageDto photoStorageDto) throws SQLException, ClassNotFoundException;
    public boolean updatePhotoStorage(PhotoStorageDto photoStorageDto) throws SQLException, ClassNotFoundException;
    public boolean existPhotoStorage(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePhotoStorage(String id) throws SQLException, ClassNotFoundException;
    public String generateNewPhotoStorageId() throws SQLException, ClassNotFoundException;
    public ArrayList<PhotoStorageDto> searchPhotoStorage(String id) throws SQLException, ClassNotFoundException;
}
