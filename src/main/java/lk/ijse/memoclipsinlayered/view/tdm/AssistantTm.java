package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AssistantTm implements Comparable<AssistantTm> {
    private String assistantId;
    private String assistantName;
    private String photographerId;
    private String availability;


    @Override
    public int compareTo(AssistantTm o) {
        return assistantId.compareTo(o.assistantId);
    }
}
