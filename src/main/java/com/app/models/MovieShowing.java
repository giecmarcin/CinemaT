package com.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Marcin on 24.04.2016.
 */

@Getter
@Setter
@Entity
public class MovieShowing {

    @Id
    @GeneratedValue
    private Long id;

    private Long CinemaId;
    private  Long CinemaHallId;
    private Long MovieId;

    private Date dateOfShow;
}
