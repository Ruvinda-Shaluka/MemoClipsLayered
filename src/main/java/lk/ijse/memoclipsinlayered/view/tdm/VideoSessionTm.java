package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VideoSessionTm implements Comparable<VideoSessionTm> {
    private String videoId;
    private String bookingId;
    private String videographerId;
    private String duration;
    private String format;


    @Override
    public int compareTo(VideoSessionTm o) {
        return videoId.compareTo(o.getVideoId());
    }
}
