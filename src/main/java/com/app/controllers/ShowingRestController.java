package com.app.controllers;


import com.app.models.Cinema;
import com.app.models.Movie;
import com.app.models.Showing;
import com.app.models.dto.DateOfMovie;
import com.app.services.CinemaService;
import com.app.services.MovieService;
import com.app.services.ShowingService;
import com.app.services.impl.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("rest/showing")
public class ShowingRestController {

    @Autowired
    private ShowingService showingService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = {"/cinema/{id}", "/cinema"})
    public ResponseEntity<List<Showing>> getAllShowingInCinema(Map<String, Object> modelMap, @PathVariable Optional<Long> id, @RequestParam(value = "date", required = false) Optional<String> d) {
        DateOfMovie dateOfMovie = new DateOfMovie();
        List<Showing> showings = null;
        if (id.isPresent()) {
            Cinema cinema = cinemaService.findOne(id.get());
            dateOfMovie.setIdCinema(cinema.getId());

            if (d.isPresent()) {
                Date choosenDate = DateUtils.getDateFromString(d.get());
                dateOfMovie.setDate(choosenDate);
            } else {
                //No date
                LocalDate currentDate = LocalDate.now();
                Date date = DateUtils.asDate(currentDate);
                dateOfMovie.setDate(date);
            }

            showings = showingService.findAllShowingByIsActiveAndCinemaAndDate(true, cinema, dateOfMovie.getDate());

        }
        return new ResponseEntity<List<Showing>>(showings, new HttpHeaders(), HttpStatus.OK);
    }
}
