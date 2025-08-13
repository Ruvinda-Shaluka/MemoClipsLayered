package lk.ijse.memoclipsinlayered.view.tdm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentTm implements Comparable<PaymentTm> {
    private String paymentId;
    private String invoiceId;
    private String amountPaid;
    private String paymentDate;
    private String paymentMethod;

    @Override
    public int compareTo(PaymentTm o) {
        return paymentId.compareTo(o.getPaymentId());
    }
}
