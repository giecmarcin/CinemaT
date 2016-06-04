package com.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Showing showing;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private Seat seat;

    @OneToOne(fetch = FetchType.EAGER)
    private Cinema cinema;

    public Booking() {
        isActive = true;
    }

    private boolean isActive;
}
