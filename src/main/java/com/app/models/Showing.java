package com.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Showing {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;

    @OneToOne
    private Cinema cinema;

    @OneToOne
    private CinemaHall cinemaHall;

    @OneToOne
    private Movie movie;

    public Showing() {
    }
}
