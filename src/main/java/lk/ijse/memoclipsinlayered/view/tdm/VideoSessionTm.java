package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VideoSessionTm {
    private String videoId;
    private String bookingId;
    private String videographerId;
    private String duration;
    private String format;
}
