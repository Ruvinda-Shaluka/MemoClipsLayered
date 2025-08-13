package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoAlbumTm implements Comparable<PhotoAlbumTm> {

    private String albumId;
    private String bookingId;
    private String albumType;
    private String price;

    @Override
    public int compareTo(PhotoAlbumTm o) {
        return albumId.compareTo(o.getAlbumId());
    }
}

