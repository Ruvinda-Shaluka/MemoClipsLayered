package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    public String generateNewSupplierId() throws SQLException, ClassNotFoundException;
    public ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
