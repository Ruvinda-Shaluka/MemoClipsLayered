package lk.ijse.memoclipsinlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoAlbumEntity {
    private String albumId;
    private String bookingId;
    private String albumType;
    private String price;
}
