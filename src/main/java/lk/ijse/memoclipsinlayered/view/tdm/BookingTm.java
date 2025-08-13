package lk.ijse.memoclipsinlayered.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class BookingTm implements Comparable<BookingTm> {
    private String bookingId;
    private String customerId;
    private String date;
    private String time;
    private String location;
    private String bookingType;
    private String bookingStatus;

    @Override
    public int compareTo(BookingTm o) {
        return bookingId.compareTo(o.getBookingId());
    }
}