package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotographerTm implements Comparable<PhotographerTm> {

    private String photographerId;
    private String name;
    private String speciality;
    private String contact;
    private String availability;

    @Override
    public int compareTo(PhotographerTm o) {
        return photographerId.compareTo(o.getPhotographerId());
    }
}
