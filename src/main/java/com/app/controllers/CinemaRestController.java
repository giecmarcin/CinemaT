package com.app.controllers;


import com.app.models.Cinema;
import com.app.models.Cinemahall;
import com.app.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/cinema")
public class CinemaRestController {

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private CinemaService cinemaHallService;

    @RequestMapping(value = "/all")
    public ResponseEntity<List<Cinema>> getAllMovies(Model model) {
        List<Cinema> movies = cinemaService.findAll();
        return new ResponseEntity<List<Cinema>>(movies, new HttpHeaders(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}/all")
//    public ResponseEntity<List<Cinemahall>> getAllHalls(Cinema model, Optional<Long> id) {
//        Cinema cinema = cinemaService.findOne(id.get());
//        List<Cinemahall> cinemhalls = cinema.getCinemahalls();
//        return new ResponseEntity<List<Cinemahall>>(cinemhalls, new HttpHeaders(), HttpStatus.OK);
//    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Cinema> getDetailsOfCinema(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Cinema cinema = cinemaService.findOne(id.get());
            if (cinema != null) {
                return new ResponseEntity<Cinema>(cinema, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Cinema>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Cinema>(HttpStatus.NOT_FOUND);
    }

    //SALE Z POSZCZEGOLNEGO KINA
    @RequestMapping(value = "/{id}/halls")
    public ResponseEntity<List<Cinemahall>> getDetailsOfCinemaHall(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Cinema cinema = cinemaService.findOne(id.get());
            List<Cinemahall> cinemahall = cinema.getCinemahalls();
            if (cinemahall != null) {
                return new ResponseEntity<List<Cinemahall>>(cinemahall, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Cinemahall>>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<List<Cinemahall>>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Void> processAddMovieForm(@RequestBody Cinema cinema) {
        cinemaService.save(cinema);

        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

}
