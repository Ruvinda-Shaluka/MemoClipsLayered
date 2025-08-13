package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.CustomerBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.CustomerDAO;
import lk.ijse.memoclipsinlayered.dto.CustomerDto;
import lk.ijse.memoclipsinlayered.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerEntity> entity = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity e : entity) {
            customerDtos.add(new CustomerDto(
                    e.getCustomerId(),
                   e.getCustomerName(),
                    e.getContactNo(),
                   e.getAddress(),
                    e.getEmail(),
                    e.getAdminId()

            ));
        }
        return customerDtos;
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new CustomerEntity(
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getContactNo(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getAdminId()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new CustomerEntity(
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getContactNo(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getAdminId()
        ));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerEntity> entity = customerDAO.search(id);
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity e : entity) {
            customerDtos.add(new CustomerDto(
                    e.getCustomerId(),
                    e.getCustomerName(),
                    e.getContactNo(),
                    e.getAddress(),
                    e.getEmail(),
                    e.getAdminId()
            ));
        }

        return customerDtos;
    }
}
