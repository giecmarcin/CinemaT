package com.app.services;

import com.app.models.Cinema;
import com.app.models.Showing;

import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 19.05.2016.
 */
public interface ShowingService extends GenericService<Showing> {
    List<Showing> findAllShowingInCinema(Long idOfCinema); //return active and disactive showings

    List<Showing> findAllShowingByIsActiveAndCinema(boolean isActive, Cinema cinema);

    List<Showing> findAllShowingByIsActiveAndCinemaAndDate(boolean isActive, Cinema cinema, Date date);
}
