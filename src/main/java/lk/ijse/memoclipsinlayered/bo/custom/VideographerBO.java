package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.VideographerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VideographerBO extends SuperBO {
    public ArrayList<VideographerDto> getAllVideographer() throws SQLException, ClassNotFoundException;
    public boolean saveVideographer(VideographerDto videographerDto) throws SQLException, ClassNotFoundException;
    public boolean updateVideographer(VideographerDto videographerDto) throws SQLException, ClassNotFoundException;
    public boolean existVideographer(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteVideographer(String id) throws SQLException, ClassNotFoundException;
    public String generateNewVideographerId() throws SQLException, ClassNotFoundException;
    public ArrayList<VideographerDto> searchVideographer(String id) throws SQLException, ClassNotFoundException;
}
