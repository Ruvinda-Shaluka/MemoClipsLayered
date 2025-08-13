package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoSessionTm implements Comparable<PhotoSessionTm> {

    private String sessionId;
    private String bookingId;
    private String photographerId;
    private String sessionType;
    private String duration;

    @Override
    public int compareTo(PhotoSessionTm o) {
        return sessionId.compareTo(o.getSessionId());
    }
}
