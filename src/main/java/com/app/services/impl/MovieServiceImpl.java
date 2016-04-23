package com.app.services.impl;

import com.app.models.Movie;
import com.app.repositories.MovieRepository;
import com.app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie findOne(Long id) {
        return movieRepository.findOne(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void delete(Movie movie) {
        if (movie != null) {
            movieRepository.delete(movie);
        }
    }
}
