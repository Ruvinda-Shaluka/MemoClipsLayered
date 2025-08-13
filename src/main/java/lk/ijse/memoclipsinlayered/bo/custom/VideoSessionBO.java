package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.VideoSessionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VideoSessionBO extends SuperBO {
    public ArrayList<VideoSessionDto> getAllVideoSession() throws SQLException, ClassNotFoundException;
    public boolean saveVideoSession(VideoSessionDto videoSessionDto) throws SQLException, ClassNotFoundException;
    public boolean updateVideoSession(VideoSessionDto videoSessionDto) throws SQLException, ClassNotFoundException;
    public boolean existVideoSession(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteVideoSession(String id) throws SQLException, ClassNotFoundException;
    public String generateNewVideoSessionId() throws SQLException, ClassNotFoundException;
    public ArrayList<VideoSessionDto> searchVideoSession(String id) throws SQLException, ClassNotFoundException;
}
