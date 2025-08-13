package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.PaymentBO;
import lk.ijse.memoclipsinlayered.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public ArrayList<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewPaymentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<PaymentDto> searchPayment(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
