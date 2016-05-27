package com.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "Showing")
public class Showing {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    //@Temporal(TemporalType.TIME)
    private String time;

    @OneToOne
    private Cinema cinema;
    //
    @OneToOne(fetch = FetchType.EAGER)
    private Cinemahall cinemahall;

    @OneToOne
    private Movie movie;

    @OneToMany
    List<Seat> allBusySeats;

    public Showing() {
    }

    private boolean isActive;
}
