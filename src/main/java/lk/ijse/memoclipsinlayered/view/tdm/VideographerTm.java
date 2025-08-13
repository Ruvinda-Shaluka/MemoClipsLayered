package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VideographerTm implements Comparable<VideographerTm> {

    private String videographerId;
    private String name;
    private String contact;
    private String availability;


    @Override
    public int compareTo(VideographerTm o) {
        return videographerId.compareTo(o.getVideographerId());
    }
}
