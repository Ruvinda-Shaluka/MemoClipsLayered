package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException;
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
    public boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;
    public String generateNewPaymentId() throws SQLException, ClassNotFoundException;
    public ArrayList<PaymentDto> searchPayment(String id) throws SQLException, ClassNotFoundException;
}
