package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;
    public boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;
    public boolean existItem(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public String generateNewItemId() throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDto> searchItem(String id) throws SQLException, ClassNotFoundException;
}
