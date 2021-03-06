package com.app.services.impl;

import com.app.models.Cinema;
import com.app.models.Cinemahall;
import com.app.models.Cinemahall;
import com.app.models.Seat;
import com.app.repositories.CinemaRepository;
import com.app.services.CinemaHallService;
import com.app.services.CinemaService;
import com.app.services.SeatService;
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

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private SeatService seatService;

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

    @Override
    public void addCinemaHall(Cinemahall cinemahall, Long cinemaId) {
        for (int i = 0; i < cinemahall.getNumberOfSeats(); i++) {
            Seat seat = new Seat(i);
            seatService.save(seat);
            cinemahall.getSeats().add(seat);
        }
        Cinemahall cH = cinemaHallService.save(cinemahall);

        Cinema cinema = cinemaRepository.findOne(cinemaId); //znalezc kino
        //utworzyc sale
        //zapisac sale

        cinema.getCinemahalls().add(cinemahall);
        System.out.println(cH.getId() + "id sali");
        cinemaRepository.save(cinema);

    }

    @Override
    public void addSeveralCinemaHalls(int amount, Long CinemaId) {

    }
}
