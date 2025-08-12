package lk.ijse.memoclipsinlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoSessionDto {

    private String sessionId;
    private String bookingId;
    private String photographerId;
    private String sessionType;
    private String duration;
}
