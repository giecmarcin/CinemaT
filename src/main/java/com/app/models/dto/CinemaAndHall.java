package com.app.models.dto;

import com.app.models.Cinema;
import com.app.models.CinemaHall;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marcin on 29.04.2016.
 */

@Getter
@Setter
public class CinemaAndHall {
    private Cinema cinema;
    private CinemaHall cinemaHall;

    public CinemaAndHall() {
    }
}