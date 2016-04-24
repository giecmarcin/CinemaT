package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;
    private String street;
    private int number;

    @OneToMany
    @JoinColumn(name = "cinema_id")
    List<CinemaHall> cinemaHalls;

    public Cinema() {
        cinemaHalls = new LinkedList<CinemaHall>();
    }
}
