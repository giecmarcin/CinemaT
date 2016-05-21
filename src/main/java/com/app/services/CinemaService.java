package com.app.services;

import com.app.models.Cinema;
import com.app.models.Cinemahall;

/**
 * Created by Marcin on 24.04.2016.
 */
public interface CinemaService extends GenericService<Cinema> {
    void addCinemaHall(Cinemahall cinemahall, Long cinemaId);

    void addSeveralCinemaHalls(int amount, Long cinemaId);
}
