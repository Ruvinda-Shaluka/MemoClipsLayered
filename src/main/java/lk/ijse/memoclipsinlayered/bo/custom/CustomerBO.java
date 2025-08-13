package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException;
}