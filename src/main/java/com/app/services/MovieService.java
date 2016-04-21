package com.app.services;

import com.app.models.Movie;

import java.util.List;

/**
 * Created by Marcin on 21.04.2016.
 */
public interface MovieService {

    Movie save(Movie movie);
    Movie findOne(Long id);
    List<Movie> findAll();
    void delete(Movie Movie);
}
