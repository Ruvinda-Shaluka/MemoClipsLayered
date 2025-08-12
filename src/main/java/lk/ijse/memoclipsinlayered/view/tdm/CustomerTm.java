package lk.ijse.memoclipsinlayered.view.tdm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerTm {
    private String customerId;
    private String customerName;
    private String contactNo;
    private String address;
    private String email;
    private String adminId;
}

