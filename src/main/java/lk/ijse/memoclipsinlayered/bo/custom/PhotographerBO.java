package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.PhotographerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PhotographerBO extends SuperBO {
    public ArrayList<PhotographerDto> getAllPhotographer() throws SQLException, ClassNotFoundException;
    public boolean savePhotographer(PhotographerDto photographerDto) throws SQLException, ClassNotFoundException;
    public boolean updatePhotographer(PhotographerDto photographerDto) throws SQLException, ClassNotFoundException;
    public boolean existPhotographer(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePhotographer(String id) throws SQLException, ClassNotFoundException;
    public String generateNewPhotographerId() throws SQLException, ClassNotFoundException;
    public ArrayList<PhotographerDto> searchPhotographer(String id) throws SQLException, ClassNotFoundException;
}
