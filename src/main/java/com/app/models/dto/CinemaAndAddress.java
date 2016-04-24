package com.app.models.dto;


import com.app.models.Address;
import com.app.models.Cinema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter

public class CinemaAndAddress {
    private Cinema cinema;
    private Address address;

    public CinemaAndAddress() {
        cinema = new Cinema();
        address = new Address();
    }
}
