package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.AlbumDetailsBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.AlbumDetailsDAO;
import lk.ijse.memoclipsinlayered.dto.AdminDto;
import lk.ijse.memoclipsinlayered.dto.AlbumDetailsDto;
import lk.ijse.memoclipsinlayered.entity.AdminEntity;
import lk.ijse.memoclipsinlayered.entity.AlbumDetailsEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumDetailBOImpl implements AlbumDetailsBO {
    AlbumDetailsDAO albumDetailsDAO = (AlbumDetailsDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.ALBUM_DETAIL);

    @Override
    public ArrayList<AlbumDetailsDto> getAllAlbumDetails() throws SQLException, ClassNotFoundException {
        ArrayList<AlbumDetailsEntity> entity= albumDetailsDAO.getAll();
        ArrayList<AlbumDetailsDto> albumDetailsDtos = new ArrayList<>();
        for (AlbumDetailsEntity e : entity){
            albumDetailsDtos.add(new AlbumDetailsDto(
                    e.getDetailId(),
                    e.getAlbumId(),
                    e.getSize(),
                    e.getCoverType(),
                    e.getPaperQuantity(),
                    e.getNumberOfPhotos()
            ));
        }
        return albumDetailsDtos;
    }

    @Override
    public boolean saveAlbumDetails(AlbumDetailsDto albumDetailsDto) throws SQLException, ClassNotFoundException {
        return albumDetailsDAO.save(new AlbumDetailsEntity(
                albumDetailsDto.getDetailId(),
                albumDetailsDto.getAlbumId(),
                albumDetailsDto.getSize(),
                albumDetailsDto.getCoverType(),
                albumDetailsDto.getPaperQuantity(),
                albumDetailsDto.getNumberOfPhotos()
        ));
    }

    @Override
    public boolean updateAlbumDetails(AlbumDetailsDto albumDetailsDto) throws SQLException, ClassNotFoundException {
        return albumDetailsDAO.update(new AlbumDetailsEntity(
                albumDetailsDto.getDetailId(),
                albumDetailsDto.getAlbumId(),
                albumDetailsDto.getSize(),
                albumDetailsDto.getCoverType(),
                albumDetailsDto.getPaperQuantity(),
                albumDetailsDto.getNumberOfPhotos()
        ));
    }

    @Override
    public boolean existAlbumDetails(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteAlbumDetails(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewAlbumDetailsId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<AlbumDetailsDto> searchAlbumDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<AlbumDetailsEntity> entity= albumDetailsDAO.search(id);
        ArrayList<AlbumDetailsDto> albumDetailsDtos = new ArrayList<>();
        for (AlbumDetailsEntity e : entity){
            albumDetailsDtos.add(new AlbumDetailsDto(
                    e.getDetailId(),
                    e.getAlbumId(),
                    e.getSize(),
                    e.getCoverType(),
                    e.getPaperQuantity(),
                    e.getNumberOfPhotos()
            ));
        }
        return albumDetailsDtos;
    }
}
