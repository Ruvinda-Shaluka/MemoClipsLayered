package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.InvoiceBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.InvoiceDAO;
import lk.ijse.memoclipsinlayered.dao.custom.impl.InvoiceDAOImpl;
import lk.ijse.memoclipsinlayered.dto.InvoiceDto;
import lk.ijse.memoclipsinlayered.entity.InvoiceEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceBOImpl implements InvoiceBO {
    InvoiceDAO invoiceDAO = (InvoiceDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.INVOICE);

    @Override
    public ArrayList<InvoiceDto> getAllInvoice() throws SQLException, ClassNotFoundException {
        ArrayList<InvoiceEntity> entity = invoiceDAO.getAll();
        ArrayList<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (InvoiceEntity e : entity) {
            invoiceDtos.add(new InvoiceDto(
                    e.getInvoiceId(),
                    e.getBookingId(),
                    e.getAmount(),
                    e.getDueDate(),
                    e.getPaymentStatus()

            ));
        }
        return invoiceDtos ;
    }

    @Override
    public boolean saveInvoice(InvoiceDto invoiceDto) throws SQLException, ClassNotFoundException {
        return invoiceDAO.save(new InvoiceEntity(
                invoiceDto.getInvoiceId(),
                invoiceDto.getBookingId(),
                invoiceDto.getAmount(),
                invoiceDto.getDueDate(),
                invoiceDto.getPaymentStatus()
        ));
    }

    @Override
    public boolean updateInvoice(InvoiceDto invoiceDto) throws SQLException, ClassNotFoundException {
        return invoiceDAO.update(new InvoiceEntity(
                invoiceDto.getInvoiceId(),
                invoiceDto.getBookingId(),
                invoiceDto.getAmount(),
                invoiceDto.getDueDate(),
                invoiceDto.getPaymentStatus()
        ));
    }

    @Override
    public boolean existInvoice(String id) throws SQLException, ClassNotFoundException {
        return invoiceDAO.exist(id);
    }

    @Override
    public boolean deleteInvoice(String id) throws SQLException, ClassNotFoundException {
        return invoiceDAO.delete(id);
    }

    @Override
    public String generateNewInvoiceId() throws SQLException, ClassNotFoundException {
        return invoiceDAO.generateNewId();
    }

    @Override
    public ArrayList<InvoiceDto> searchInvoice(String id) throws SQLException, ClassNotFoundException {
        ArrayList<InvoiceEntity> entity = invoiceDAO.search(id);
        ArrayList<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (InvoiceEntity e : entity) {
            invoiceDtos.add(new InvoiceDto(
                    e.getInvoiceId(),
                    e.getBookingId(),
                    e.getAmount(),
                    e.getDueDate(),
                    e.getPaymentStatus()

            ));
        }
        return invoiceDtos;
    }
}
