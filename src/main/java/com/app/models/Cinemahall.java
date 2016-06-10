package com.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity(name="Cinemahall")
public class Cinemahall {
    @OneToMany
    @JoinColumn(name = "cinemaHall_id")
    @JsonIgnore
    List<Seat> seats;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int numberOfSeats;

    public Cinemahall() {
        seats = new LinkedList<Seat>();
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}