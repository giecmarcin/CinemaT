package com.app.services;

import com.app.models.Showing;

import java.util.List;

/**
 * Created by Marcin on 19.05.2016.
 */
public interface ShowingService extends GenericService<Showing> {
    List<Showing> findAllShowingInCinema(Long idOfCinema);
}
