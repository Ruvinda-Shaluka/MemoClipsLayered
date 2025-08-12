package lk.ijse.memoclipsinlayered.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentEntity {
    private String paymentId;
    private String invoiceId;
    private String amountPaid;
    private String paymentDate;
    private String paymentMethod;
}
