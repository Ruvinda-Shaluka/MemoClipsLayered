package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.VideoSessionBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.VideoSessionDAO;
import lk.ijse.memoclipsinlayered.dto.VideoSessionDto;
import lk.ijse.memoclipsinlayered.entity.VideoSessionEntity;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoSessionBOImpl implements VideoSessionBO {
    VideoSessionDAO videoSessionDAO = (VideoSessionDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.VIDEO_SESSION);
    @Override
    public ArrayList<VideoSessionDto> getAllVideoSession() throws SQLException, ClassNotFoundException {
        ArrayList<VideoSessionEntity> entities = videoSessionDAO.getAll();
        ArrayList<VideoSessionDto> videoSessionDtos = new ArrayList<>();
        for (VideoSessionEntity entity : entities) {
            videoSessionDtos.add(new VideoSessionDto(
                    entity.getVideoId(),
                    entity.getBookingId(),
                    entity.getVideographerId(),
                    entity.getDuration(),
                    entity.getFormat()
            ));
        }
        return videoSessionDtos;
    }

    @Override
    public boolean saveVideoSession(VideoSessionDto videoSessionDto) throws SQLException, ClassNotFoundException {
        return videoSessionDAO.save(new VideoSessionEntity(
                videoSessionDto.getVideoId(),
                videoSessionDto.getBookingId(),
                videoSessionDto.getVideographerId(),
                videoSessionDto.getDuration(),
                videoSessionDto.getFormat()
        ));
    }

    @Override
    public boolean updateVideoSession(VideoSessionDto videoSessionDto) throws SQLException, ClassNotFoundException {
        return videoSessionDAO.update(new VideoSessionEntity(
                videoSessionDto.getVideoId(),
                videoSessionDto.getBookingId(),
                videoSessionDto.getVideographerId(),
                videoSessionDto.getDuration(),
                videoSessionDto.getFormat()
        ));
    }

    @Override
    public boolean existVideoSession(String id) throws SQLException, ClassNotFoundException {
        return videoSessionDAO.exist(id);
    }

    @Override
    public boolean deleteVideoSession(String id) throws SQLException, ClassNotFoundException {
        return videoSessionDAO.delete(id);
    }

    @Override
    public String generateNewVideoSessionId() throws SQLException, ClassNotFoundException {
        return videoSessionDAO.generateNewId();
    }

    @Override
    public ArrayList<VideoSessionDto> searchVideoSession(String id) throws SQLException, ClassNotFoundException {
        ArrayList<VideoSessionEntity> entities = videoSessionDAO.search(id);
        ArrayList<VideoSessionDto> videoSessionDtos = new ArrayList<>();
        for (VideoSessionEntity entity : entities) {
            videoSessionDtos.add(new VideoSessionDto(
                    entity.getVideoId(),
                    entity.getBookingId(),
                    entity.getVideographerId(),
                    entity.getDuration(),
                    entity.getFormat()
            ));
        }
        return videoSessionDtos;
    }
}
