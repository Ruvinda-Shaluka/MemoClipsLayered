package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoStorageTm implements Comparable<PhotoStorageTm> {
    private String photoId;
    private String sessionId;
    private String uploadDate;
    private String uploadTime;

    @Override
    public int compareTo(PhotoStorageTm o) {
        return photoId.compareTo(o.getPhotoId());
    }
}
