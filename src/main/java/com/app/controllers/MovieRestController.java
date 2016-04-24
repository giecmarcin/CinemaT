package com.app.controllers;

import com.app.models.Movie;
import com.app.models.User;
import com.app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin on 24.04.2016.
 */
@RestController
@RequestMapping("rest/movie")
public class MovieRestController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/all")
    public ResponseEntity<List<Movie>> getAllMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        return new ResponseEntity<List<Movie>>(movies, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Movie> getDetailsOfMovie(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Movie movie = movieService.findOne(id.get());
            if (movie != null) {
                return new ResponseEntity<Movie>(movie, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
    }
}
