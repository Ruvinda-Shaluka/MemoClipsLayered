package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotoAlbumBO;
import lk.ijse.memoclipsinlayered.dto.PhotoAlbumDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoAlbumBOImpl implements PhotoAlbumBO {
    @Override
    public ArrayList<PhotoAlbumDto> getAllPhotoAlbum() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePhotoAlbum(PhotoAlbumDto photoAlbumDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePhotoAlbum(PhotoAlbumDto photoAlbumDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existPhotoAlbum(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletePhotoAlbum(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewPhotoAlbumId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<PhotoAlbumDto> searchPhotoAlbum(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
