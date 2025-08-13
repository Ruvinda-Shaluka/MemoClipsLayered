package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.SupplierBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.SupplierDAO;
import lk.ijse.memoclipsinlayered.dto.SupplierDto;
import lk.ijse.memoclipsinlayered.entity.SupplierEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierEntity> entities = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (SupplierEntity entity : entities) {
            supplierDtos.add(new SupplierDto(
                    entity.getSupplierId(),
                    entity.getName(),
                    entity.getContact(),
                    entity.getSupplyQuantity()
            ));
        }
        return supplierDtos;
    }

    @Override
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new SupplierEntity(
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getSupplyQuantity()
        ));
    }

    @Override
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new SupplierEntity(
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getSupplyQuantity()
        ));
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNewSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewId();
    }

    @Override
    public ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException {
        ArrayList<SupplierEntity> entities = supplierDAO.search(id);
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (SupplierEntity entity : entities) {
            supplierDtos.add(new SupplierDto(
                    entity.getSupplierId(),
                    entity.getName(),
                    entity.getContact(),
                    entity.getSupplyQuantity()
            ));
        }
        return supplierDtos;
    }
}
