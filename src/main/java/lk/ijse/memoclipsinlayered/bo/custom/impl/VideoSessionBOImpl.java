package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.VideoSessionBO;
import lk.ijse.memoclipsinlayered.dto.VideoSessionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class VideoSessionBOImpl implements VideoSessionBO {
    @Override
    public ArrayList<VideoSessionDto> getAllVideoSession() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveVideoSession(VideoSessionDto videoSessionDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateVideoSession(VideoSessionDto videoSessionDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existVideoSession(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteVideoSession(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewVideoSessionId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<VideoSessionDto> searchVideoSession(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
