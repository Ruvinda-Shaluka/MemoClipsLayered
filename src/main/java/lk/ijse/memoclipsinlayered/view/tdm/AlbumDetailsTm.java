package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data


public class AlbumDetailsTm implements Comparable<AlbumDetailsTm> {
    private String detailId;
    private String albumId;
    private String numberOfPhotos;
    private String coverType;
    private String size;
    private String paperQuantity;

    @Override
    public int compareTo(AlbumDetailsTm o) {
        return detailId.compareTo(o.detailId);
    }
}
