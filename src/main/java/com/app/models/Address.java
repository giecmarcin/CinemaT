package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Marcin on 15.04.2016.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String zipcode;
    private String street;
    private int streetNumber;

    @OneToOne(mappedBy = "address")
    private Cinema cinema;
}
