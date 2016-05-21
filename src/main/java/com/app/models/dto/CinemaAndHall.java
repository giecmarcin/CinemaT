package com.app.models.dto;

import com.app.models.Cinema;
import com.app.models.Cinemahall;
import com.app.models.Cinemahall;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marcin on 29.04.2016.
 */

@Getter
@Setter
public class CinemaAndHall {
    private Cinema cinema;
    private Cinemahall cinemahall;

    public CinemaAndHall() {
    }
}