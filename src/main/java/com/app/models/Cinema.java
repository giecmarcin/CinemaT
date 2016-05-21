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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    List<Cinemahall> cinemahalls;

    public Cinema() {
        cinemahalls = new LinkedList<Cinemahall>();
    }

    @Override
    public String toString() {
        return name + " " + city + " ul. " + street + " " + number;
    }
}
