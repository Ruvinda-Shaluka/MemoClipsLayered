package lk.ijse.memoclipsinlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VideoSessionEntity {
    private String videoId;
    private String bookingId;
    private String videographerId;
    private String duration;
    private String format;
}
