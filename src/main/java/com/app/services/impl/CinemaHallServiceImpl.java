package com.app.services.impl;

import com.app.models.Cinemahall;
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
    public Cinemahall save(Cinemahall cinemahall) {
        return cinemaHallRepository.save(cinemahall);
    }

    @Override
    public Cinemahall findOne(Long id) {
        return cinemaHallRepository.findOne(id);
    }

    @Override
    public List<Cinemahall> findAll() {
        return cinemaHallRepository.findAll();
    }

    @Override
    public void delete(Cinemahall cinemahall) {
        cinemaHallRepository.delete(cinemahall);
    }
}
