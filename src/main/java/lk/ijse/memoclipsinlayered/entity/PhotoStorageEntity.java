package lk.ijse.memoclipsinlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoStorageEntity {
private String photoId;
private String sessionId;
private String uploadDate;
private String uploadTime;

}
