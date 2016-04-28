package com.app.services.impl;

import com.app.models.CinemaHall;
import com.app.repositories.CinemaHallRepository;
import com.app.services.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    private CinemaHallRepository cinemaHallRepository;

    @Override
    public CinemaHall save(CinemaHall cinemaHall) {
        return cinemaHallRepository.save(cinemaHall);
    }

    @Override
    public CinemaHall findOne(Long id) {
        return cinemaHallRepository.findOne(id);
    }

    @Override
    public List<CinemaHall> findAll() {
        return cinemaHallRepository.findAll();
    }

    @Override
    public void delete(CinemaHall cinemaHall) {
        cinemaHallRepository.delete(cinemaHall);
    }
}
