package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.SupplierBO;
import lk.ijse.memoclipsinlayered.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewSupplierId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
