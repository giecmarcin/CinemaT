package com.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Showing {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @OneToOne
    private Cinema cinema;

    @OneToOne
    private CinemaHall cinemaHall;

    @OneToOne
    private Movie movie;

    public Showing() {
    }
}
