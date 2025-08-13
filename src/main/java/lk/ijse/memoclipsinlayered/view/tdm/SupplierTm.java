package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplierTm implements Comparable<SupplierTm> {
    private String supplierId;
    private String name;
    private String contact;
    private String supplyQuantity;

    @Override
    public int compareTo(SupplierTm o) {
        return supplierId.compareTo(o.getSupplierId());
    }
}
