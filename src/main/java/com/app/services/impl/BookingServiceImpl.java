package com.app.services.impl;

import com.app.models.Booking;
import com.app.models.Showing;
import com.app.models.User;
import com.app.repositories.BookingRepository;
import com.app.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

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
