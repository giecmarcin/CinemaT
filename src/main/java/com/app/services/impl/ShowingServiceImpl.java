package com.app.services.impl;

import com.app.models.Cinema;
import com.app.models.Showing;
import com.app.repositories.ShowingRepository;
import com.app.services.CinemaService;
import com.app.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ShowingServiceImpl implements ShowingService {
    @Autowired
    CinemaService cinemaService;

    @Autowired
    private ShowingRepository showingRepository;

    @Override
    public Showing save(Showing showing) {
        return showingRepository.save(showing);
    }

    @Override
    public Showing findOne(Long id) {
        return showingRepository.findOne(id);
    }

    @Override
    public List<Showing> findAll() {
        return showingRepository.findAll();
    }

    @Override
    public void delete(Showing showing) {
        showingRepository.delete(showing);
    }

    @Override
    public List<Showing> findAllShowingInCinema(Long idOfCinema) {
        Cinema cinema = cinemaService.findOne(idOfCinema);
        return showingRepository.findByCinema(cinema);
    }

    @Override
    public List<Showing> findAllShowingByIsActiveAndCinema(boolean isActive, Cinema cinema) {
        return showingRepository.findByIsActiveAndCinema(isActive, cinema);
    }

    @Override
    public List<Showing> findAllShowingByIsActiveAndCinemaAndDate(boolean isActive, Cinema cinema, Date date) {
        return showingRepository.findByIsActiveAndCinemaAndDate(isActive, cinema, date);
    }

}
