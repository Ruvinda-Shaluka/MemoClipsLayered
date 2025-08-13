package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.PaymentDAO;
import lk.ijse.memoclipsinlayered.entity.PaymentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    public boolean save(PaymentEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Payment (paymentId, invoiceId, amountPaid, paymentDate, paymentMethod) VALUES (?, ?, ?, ?, ?)",
                dto.getPaymentId(),
                dto.getInvoiceId(),
                dto.getAmountPaid(),
                dto.getPaymentDate(),
                dto.getPaymentMethod()
        );
    }

    public boolean update(PaymentEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE payments SET invoiceId = ?, amountPaid = ?, paymentDate = ?, paymentMethod = ? WHERE paymentId = ?",
                dto.getInvoiceId(),
                dto.getAmountPaid(),
                dto.getPaymentDate(),
                dto.getPaymentMethod(),
                dto.getPaymentId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM payment WHERE paymentId = ?", paymentId);
    }

    public ArrayList<PaymentEntity> search(String paymentId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM payment WHERE paymentId = ?", paymentId);
        ArrayList<PaymentEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new PaymentEntity(
                    rs.getString("paymentId"),
                    rs.getString("invoiceId"),
                    rs.getString("amountPaid"),
                    rs.getString("paymentDate"),
                    rs.getString("paymentMethod")
            ));
        }
        return list;
    }

    public ArrayList<PaymentEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Payments");
        ArrayList<PaymentEntity> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PaymentEntity(
                    rs.getString("paymentId"),
                    rs.getString("invoiceId"),
                    rs.getString("amountPaid"),
                    rs.getString("paymentDate"),
                    rs.getString("paymentMethod")
            ));
        }
        return list;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT paymentId FROM payments ORDER BY paymentId DESC LIMIT 1");
        String prefix = "PAY";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }
/*
    public ArrayList<String> getAllPaymentIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT paymentId FROM payments");
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString(1));
        }
        return ids;
    }*/
}
