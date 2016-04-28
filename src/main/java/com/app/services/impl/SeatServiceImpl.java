package com.app.services.impl;


import com.app.models.Seat;
import com.app.repositories.SeatRepository;
import com.app.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seat findOne(Long id) {
        return seatRepository.findOne(id);
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public void delete(Seat seat) {
        seatRepository.save(seat);
    }
}
