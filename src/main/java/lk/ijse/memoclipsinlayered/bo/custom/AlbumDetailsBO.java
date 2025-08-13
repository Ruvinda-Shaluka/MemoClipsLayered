package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dao.custom.AlbumDetailsDAO;
import lk.ijse.memoclipsinlayered.dto.AlbumDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AlbumDetailsBO extends SuperBO {
    public ArrayList<AlbumDetailsDto> getAllAlbumDetails() throws SQLException, ClassNotFoundException;
    public boolean saveAlbumDetails(AlbumDetailsDto albumDetailsDto) throws SQLException, ClassNotFoundException;
    public boolean updateAlbumDetails(AlbumDetailsDto albumDetailsDto) throws SQLException, ClassNotFoundException;
    public boolean existAlbumDetails(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteAlbumDetails(String id) throws SQLException, ClassNotFoundException;
    public String generateNewAlbumDetailsId() throws SQLException, ClassNotFoundException;
    public ArrayList<AlbumDetailsDto> searchAlbumDetails(String id) throws SQLException, ClassNotFoundException;
}
