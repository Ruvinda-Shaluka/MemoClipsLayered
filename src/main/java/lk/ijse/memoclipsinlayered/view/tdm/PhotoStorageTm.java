package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoStorageTm {
    private String photoId;
    private String sessionId;
    private String uploadDate;
    private String uploadTime;

}
