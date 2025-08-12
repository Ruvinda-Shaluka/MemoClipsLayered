package lk.ijse.memoclipsinlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoSessionEntity {

    private String sessionId;
    private String bookingId;
    private String photographerId;
    private String sessionType;
    private String duration;
}
