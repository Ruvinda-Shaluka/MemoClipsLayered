package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.ItemBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.ItemDAO;
import lk.ijse.memoclipsinlayered.dto.ItemDto;
import lk.ijse.memoclipsinlayered.entity.ItemEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemEntity> entity = itemDAO.getAll();
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (ItemEntity e : entity) {
            itemDtos.add(new ItemDto(
                    e.getItemId(),
                    e.getItemName(),
                    e.getQuantity(),
                    e.getLastUpdateDate(),
                    e.getSupplierId()

            ));
        }
        return itemDtos;
    }

    @Override
    public boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new ItemEntity(
                itemDto.getItemId(),
                itemDto.getItemName(),
                itemDto.getQuantity(),
                itemDto.getLastUpdateDate(),
                itemDto.getSupplierId()
        ));
    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new ItemEntity(
                itemDto.getItemId(),
                itemDto.getItemName(),
                itemDto.getQuantity(),
                itemDto.getLastUpdateDate(),
                itemDto.getSupplierId()
        ));
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    @Override
    public ArrayList<ItemDto> searchItem(String id) throws SQLException, ClassNotFoundException {
        ArrayList<ItemEntity> entity = itemDAO.search(id);
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (ItemEntity e : entity) {
            itemDtos.add(new ItemDto(
                    e.getItemId(),
                    e.getItemName(),
                    e.getQuantity(),
                    e.getLastUpdateDate(),
                    e.getSupplierId()
            ));
        }
        return itemDtos;
    }
}
