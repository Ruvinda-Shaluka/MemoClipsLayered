package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.PhotoAlbumDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PhotoAlbumBO extends SuperBO {
    public ArrayList<PhotoAlbumDto> getAllPhotoAlbum() throws SQLException, ClassNotFoundException;
    public boolean savePhotoAlbum(PhotoAlbumDto photoAlbumDto) throws SQLException, ClassNotFoundException;
    public boolean updatePhotoAlbum(PhotoAlbumDto photoAlbumDto) throws SQLException, ClassNotFoundException;
    public boolean existPhotoAlbum(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePhotoAlbum(String id) throws SQLException, ClassNotFoundException;
    public String generateNewPhotoAlbumId() throws SQLException, ClassNotFoundException;
    public ArrayList<PhotoAlbumDto> searchPhotoAlbum(String id) throws SQLException, ClassNotFoundException;
}
