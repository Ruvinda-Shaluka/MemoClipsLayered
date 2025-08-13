package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PaymentBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.PaymentDAO;
import lk.ijse.memoclipsinlayered.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.memoclipsinlayered.dto.PaymentDto;
import lk.ijse.memoclipsinlayered.entity.PaymentEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO =(PaymentDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public ArrayList<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentEntity> entity = paymentDAO.getAll();
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity e : entity) {
            paymentDtos.add(new PaymentDto(
                    e.getPaymentId(),
                    e.getInvoiceId(),
                    e.getAmountPaid(),
                    e.getPaymentDate(),
                    e.getPaymentMethod()

            ));
        }
        return paymentDtos;
    }

    @Override
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new PaymentEntity(
                paymentDto.getPaymentId(),
                paymentDto.getInvoiceId(),
                paymentDto.getAmountPaid(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentMethod()
        ));
    }

    @Override
    public boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new PaymentEntity(
                paymentDto.getPaymentId(),
                paymentDto.getInvoiceId(),
                paymentDto.getAmountPaid(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentMethod()
        ));
    }

    @Override
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.exist(id);
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public String generateNewPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewId();
    }

    @Override
    public ArrayList<PaymentDto> searchPayment(String id) throws SQLException, ClassNotFoundException {
        ArrayList<PaymentEntity> entity = paymentDAO.search(id);
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        for (PaymentEntity e : entity) {
            paymentDtos.add(new PaymentDto(
                    e.getPaymentId(),
                    e.getInvoiceId(),
                    e.getAmountPaid(),
                    e.getPaymentDate(),
                    e.getPaymentMethod()

            ));
        }
        return paymentDtos;

    }
}
