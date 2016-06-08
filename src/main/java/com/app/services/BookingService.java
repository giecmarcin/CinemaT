package com.app.services;

import com.app.models.Booking;
import com.app.models.Cinema;
import com.app.models.Seat;
import com.app.models.User;
import com.app.models.dto.BookingAndSeat;

import java.util.List;

public interface BookingService extends GenericService<Booking> {

    List<Seat> findAvailableSeats(Long idOfShowing);

    boolean bookAFewSeat(String ListOfSeatsId, User user, BookingAndSeat bookingAndSeat);
    List<Booking> findByCinema(Cinema cinema);
    List<Booking> findByCinemaAndIsActive(Cinema cinema, boolean isActive);
    List<Booking> findByCinemaAndIsActiveAndUser(Cinema cinema, boolean isActive, User user);
}
