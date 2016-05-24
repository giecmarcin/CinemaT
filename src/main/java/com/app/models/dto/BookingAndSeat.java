package com.app.models.dto;

import com.app.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingAndSeat {
    private Booking booking;
    private Long id;

    public BookingAndSeat() {
        booking = new Booking();
    }
}
