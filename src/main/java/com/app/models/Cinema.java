package com.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Cinema")
public class Cinema {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    @JsonIgnore
    List<Cinemahall> cinemahalls;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;
    private String street;
    private int number;

    public Cinema() {
        cinemahalls = new LinkedList<Cinemahall>();
    }

    @Override
    public String toString() {
        return name + " " + city + " ul. " + street + " " + number;
    }
}
