package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.CustomerDAO;
import lk.ijse.memoclipsinlayered.entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(CustomerEntity customerDto) throws SQLException , ClassNotFoundException{
        return SQLUtil.executeUpdate(
                "INSERT INTO Customer VALUES(?,?,?,?,?,?)",
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getContactNo(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getAdminId()
        );
    }
    @Override
    public boolean update(CustomerEntity customerDto) throws ClassNotFoundException , SQLException{
        return SQLUtil.executeUpdate(
                "UPDATE customer SET name = ? , contact = ? , address = ? , email = ? , adminId = ? WHERE customerId = ?",
                customerDto.getCustomerName(),
                customerDto.getContactNo(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getAdminId(),
                customerDto.getCustomerId()

        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String custId) throws ClassNotFoundException , SQLException{
        return SQLUtil.executeUpdate("DELETE FROM customer WHERE customerId = ?",
                custId
        );
    }

    @Override
    public ArrayList<CustomerEntity> search(String custId) throws SQLException , ClassNotFoundException{
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM customer WHERE customerId = ?",
                custId
        );
        ArrayList<CustomerEntity> list = new ArrayList<>();
        if(resultSet.next()){list.add(
            new CustomerEntity(
                    resultSet.getString("customerId"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("adminId")
            ));
        }
        return list;
    }

    @Override
    public ArrayList<CustomerEntity> getAll() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM customer");
        ArrayList<CustomerEntity> customerDtoArrayList = new ArrayList<>();

        while (resultSet.next()){customerDtoArrayList.add(
            new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return customerDtoArrayList;
    }

    @Override
    public String generateNewId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = SQLUtil.executeQuery("SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1");
        String  tableChartacter = "CUS";

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(tableChartacter.length());
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChartacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableChartacter + "001";
    }

/*    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst =  Sql.("SELECT customerId FROM customer");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()){
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }*/

}
