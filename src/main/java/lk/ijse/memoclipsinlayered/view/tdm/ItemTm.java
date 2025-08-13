package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class ItemTm implements Comparable<ItemTm>{
    private String itemId;
    private String itemName;
    private String quantity;
    private String lastUpdateDate;
    private String supplierId;

    @Override
    public int compareTo(ItemTm o) {
        return itemId.compareTo(o.getItemId());
    }
}
