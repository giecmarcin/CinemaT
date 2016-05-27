package com.app.services.impl;

import com.app.models.*;
import com.app.models.dto.BookingAndSeat;
import com.app.repositories.BookingRepository;
import com.app.services.BookingService;
import com.app.services.SeatService;
import com.app.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SeatService seatService;

    @Autowired
    private ShowingService showingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> findByShowing(Showing showing) {
        return bookingRepository.findByShowing(showing);
    }

    @Override
    public List<Booking> findByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<Seat> findAvailableSeats(Long idOfShowing) {
        Showing showing = showingService.findOne(idOfShowing);
        Cinemahall cinemahall = showing.getCinemahall();
        List<Seat> availableSeats = cinemahall.getSeats();
        boolean isSuccess = availableSeats.removeAll(showing.getAllBusySeats());
        return availableSeats;
    }

    @Override
    public boolean bookAFewSeat(String listOfSeatsId, User user, BookingAndSeat bookingAndSeat) {
        boolean isScuccess = false;
        String[] tempId = listOfSeatsId.split(",");
        for (int i = 0; i < tempId.length; i++) {
            Long seatId = Long.parseLong(tempId[i]);
            Seat seat = seatService.findOne(seatId);

            Showing showing = showingService.findOne(bookingAndSeat.getBooking().getShowing().getId());
            List<Seat> busySeats = showing.getAllBusySeats();
            busySeats.add(seat);
            showingService.save(showing);

            bookingAndSeat.getBooking().setShowing(showing);
            bookingAndSeat.getBooking().setUser(user);
            bookingAndSeat.getBooking().setSeat(seatService.findOne(seatId));
            bookingRepository.save(bookingAndSeat.getBooking());
            isScuccess = true;
        }
        return isScuccess;
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking findOne(Long id) {
        return bookingRepository.findOne(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }
}
