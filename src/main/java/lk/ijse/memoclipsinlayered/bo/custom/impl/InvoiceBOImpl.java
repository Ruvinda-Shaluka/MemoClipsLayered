package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.InvoiceBO;
import lk.ijse.memoclipsinlayered.dto.InvoiceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceBOImpl implements InvoiceBO {
    @Override
    public ArrayList<InvoiceDto> getAllInvoice() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveInvoice(InvoiceDto invoiceDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateInvoice(InvoiceDto invoiceDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existInvoice(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteInvoice(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewInvoiceId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<InvoiceDto> searchInvoice(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
