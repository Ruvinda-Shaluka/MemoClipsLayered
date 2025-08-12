package lk.ijse.memoclipsinlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class ItemEntity {
    private String itemId;
    private String itemName;
    private String quantity;
    private String lastUpdateDate;
    private String supplierId;
}
