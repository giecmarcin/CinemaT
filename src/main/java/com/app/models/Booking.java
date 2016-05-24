package com.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Showing showing;

    @OneToOne
    private User user;

    @OneToOne
    private Seat seat;

    public Booking() {
    }
}
