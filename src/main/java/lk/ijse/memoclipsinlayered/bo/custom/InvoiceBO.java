package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.InvoiceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InvoiceBO extends SuperBO {
    public ArrayList<InvoiceDto> getAllInvoice() throws SQLException, ClassNotFoundException;
    public boolean saveInvoice(InvoiceDto invoiceDto) throws SQLException, ClassNotFoundException;
    public boolean updateInvoice(InvoiceDto invoiceDto) throws SQLException, ClassNotFoundException;
    public boolean existInvoice(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteInvoice(String id) throws SQLException, ClassNotFoundException;
    public String generateNewInvoiceId() throws SQLException, ClassNotFoundException;
    public ArrayList<InvoiceDto> searchInvoice(String id) throws SQLException, ClassNotFoundException;
}
