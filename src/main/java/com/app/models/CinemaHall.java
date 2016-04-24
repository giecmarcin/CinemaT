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
public class CinemaHall {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "cinemaHall_id")
    List<Seat> seats;

    public CinemaHall() {
        seats = new LinkedList<Seat>();
    }
}
