package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PhotoAlbumBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.PhotoAlbumDAO;
import lk.ijse.memoclipsinlayered.dto.PhotoAlbumDto;
import lk.ijse.memoclipsinlayered.entity.PhotoAlbumEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoAlbumBOImpl implements PhotoAlbumBO {
    PhotoAlbumDAO photoAlbumDAO = (PhotoAlbumDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.PHOTO_ALBUM);
    @Override
    public ArrayList<PhotoAlbumDto> getAllPhotoAlbum() throws SQLException, ClassNotFoundException {
        ArrayList<PhotoAlbumEntity> entities = photoAlbumDAO.getAll();
        ArrayList<PhotoAlbumDto> photoAlbumDtos = new ArrayList<>();
        for (PhotoAlbumEntity entity : entities) {
            photoAlbumDtos.add(new PhotoAlbumDto(
                    entity.getAlbumId(),
                    entity.getBookingId(),
                    entity.getAlbumType(),
                    entity.getPrice()
            ));
        }
        return photoAlbumDtos;
    }

    @Override
    public boolean savePhotoAlbum(PhotoAlbumDto photoAlbumDto) throws SQLException, ClassNotFoundException {
        return photoAlbumDAO.save(new PhotoAlbumEntity(
                photoAlbumDto.getAlbumId(),
                photoAlbumDto.getBookingId(),
                photoAlbumDto.getAlbumType(),
                photoAlbumDto.getPrice()
        ));
    }

    @Override
    public boolean updatePhotoAlbum(PhotoAlbumDto photoAlbumDto) throws SQLException, ClassNotFoundException {
        return photoAlbumDAO.update(new PhotoAlbumEntity(
                photoAlbumDto.getAlbumId(),
                photoAlbumDto.getBookingId(),
                photoAlbumDto.getAlbumType(),
                photoAlbumDto.getPrice()
        ));
    }

    @Override
    public boolean existPhotoAlbum(String id) throws SQLException, ClassNotFoundException {
        return photoAlbumDAO.exist(id);
    }

    @Override
    public boolean deletePhotoAlbum(String id) throws SQLException, ClassNotFoundException {
        return photoAlbumDAO.delete(id);
    }

    @Override
    public String generateNewPhotoAlbumId() throws SQLException, ClassNotFoundException {
        return photoAlbumDAO.generateNewId();
    }

    @Override
    public ArrayList<PhotoAlbumDto> searchPhotoAlbum(String id) throws SQLException, ClassNotFoundException {
        ArrayList<PhotoAlbumEntity> entities = photoAlbumDAO.search(id);
        ArrayList<PhotoAlbumDto> photoAlbumDtos = new ArrayList<>();
        for (PhotoAlbumEntity entity : entities) {
            photoAlbumDtos.add(new PhotoAlbumDto(
                    entity.getAlbumId(),
                    entity.getBookingId(),
                    entity.getAlbumType(),
                    entity.getPrice()
            ));
        }
        return photoAlbumDtos;
    }
}
