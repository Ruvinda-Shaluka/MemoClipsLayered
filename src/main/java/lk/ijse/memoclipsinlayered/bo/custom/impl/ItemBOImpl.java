package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.ItemBO;
import lk.ijse.memoclipsinlayered.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<ItemDto> searchItem(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
