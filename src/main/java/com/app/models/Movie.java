package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Override
    public String toString() {
        return id + " " + title ;
    }
}
