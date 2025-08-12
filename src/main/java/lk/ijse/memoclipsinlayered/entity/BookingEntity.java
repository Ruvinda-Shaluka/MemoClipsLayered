package lk.ijse.memoclipsinlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class BookingEntity {
    private String bookingId;
    private String customerId;
    private String date;
    private String time;
    private String location;
    private String bookingType;
    private String bookingStatus;
}
