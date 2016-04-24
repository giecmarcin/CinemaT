package com.app.services.impl;

import com.app.models.Cinema;
import com.app.repositories.CinemaRepository;
import com.app.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcin on 24.04.2016.
 */
@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema findOne(Long id) {
        return cinemaRepository.findOne(id);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public void delete(Cinema cinema) {
        cinemaRepository.delete(cinema);
    }
}
