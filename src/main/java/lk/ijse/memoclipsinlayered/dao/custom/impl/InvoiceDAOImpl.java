package lk.ijse.memoclipsinlayered.dao.custom.impl;

import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dao.custom.InvoiceDAO;
import lk.ijse.memoclipsinlayered.dto.InvoiceDto;
import lk.ijse.memoclipsinlayered.entity.InvoiceEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDAOImpl implements InvoiceDAO {
    @Override
    public boolean save(InvoiceEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Invoice (invoiceId, bookingId, amount, dueDate, paymentStatus) VALUES (?, ?, ?, ?, ?)",
                dto.getInvoiceId(),
                dto.getBookingId(),
                dto.getAmount(),
                dto.getDueDate(),
                dto.getPaymentStatus()
        );
    }
    @Override
    public boolean update(InvoiceEntity dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE Invoice SET bookingId = ?, amount = ?, dueDate = ?, paymentStatus = ? WHERE invoiceId = ?",
                dto.getBookingId(),
                dto.getAmount(),
                dto.getDueDate(),
                dto.getPaymentStatus(),
                dto.getInvoiceId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean delete(String invoiceId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Invoice WHERE invoiceId = ?", invoiceId);
    }
    @Override
    public ArrayList<InvoiceEntity> search(String invoiceId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Invoice WHERE invoiceId = ?", invoiceId);
        ArrayList<InvoiceEntity> list = new ArrayList<>();
        if (rs.next()) {list.add(
            new InvoiceEntity(
                    rs.getString("invoiceId"),
                    rs.getString("bookingId"),
                    rs.getString("amount"),
                    rs.getString("dueDate"),
                    rs.getString("paymentStatus")
            ));
        }
        return list;
    }
    @Override
    public ArrayList<InvoiceEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT * FROM Invoice");
        ArrayList<InvoiceEntity> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new InvoiceEntity(
                    rs.getString("invoiceId"),
                    rs.getString("bookingId"),
                    rs.getString("amount"),
                    rs.getString("dueDate"),
                    rs.getString("paymentStatus")
            ));
        }

        return list;
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.executeQuery("SELECT invoiceId FROM Invoice ORDER BY invoiceId DESC LIMIT 1");
        String prefix = "INV";

        if (rs.next()) {
            String lastId = rs.getString(1);
            String numberPart = lastId.substring(prefix.length());
            int number = Integer.parseInt(numberPart);
            return String.format(prefix + "%03d", number + 1);
        }

        return prefix + "001";
    }
/*
    public ArrayList<String> getAllInvoiceIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT invoiceId FROM Invoice");
        ArrayList<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(rs.getString(1));
        }

        return list;
    }

    public ArrayList<InvoiceDto> getInvoicesByBookingId(String bookingId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Invoice WHERE bookingId = ?", bookingId);
        ArrayList<InvoiceDto> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new InvoiceDto(
                    rs.getString("invoiceId"),
                    rs.getString("bookingId"),
                    rs.getString("amount"),
                    rs.getString("dueDate"),
                    rs.getString("paymentStatus")
            ));
        }

        return list;
    }*/
}
