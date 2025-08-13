package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AdminTm implements  Comparable<AdminTm>{
    private String adminId;
    private String name;
    private String password;
    private String role;
    private String email;

    @Override
    public int compareTo(AdminTm o) {
        return adminId.compareTo(o.getAdminId());
    }
}
