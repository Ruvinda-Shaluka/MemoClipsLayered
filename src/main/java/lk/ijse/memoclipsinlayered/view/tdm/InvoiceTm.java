package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class InvoiceTm implements Comparable<InvoiceTm> {
    private String invoiceId;
    private String bookingId;
    private String amount;
    private String dueDate;
    private String PaymentStatus;

    @Override
    public int compareTo(InvoiceTm o) {
        return invoiceId.compareTo(o.getInvoiceId());
    }
}
