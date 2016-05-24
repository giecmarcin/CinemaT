package com.app.services;

import com.app.models.Booking;
import com.app.models.Seat;
import com.app.models.Showing;
import com.app.models.User;

import java.util.List;

public interface BookingService extends GenericService<Booking> {
    List<Booking> findByShowing(Showing showing);

    List<Booking> findByUser(User user);

    List<Seat> findAvailableSeats(Long idOfShowing);
}
