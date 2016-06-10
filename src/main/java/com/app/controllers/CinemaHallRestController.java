package com.app.controllers;

import com.app.models.Cinemahall;
import com.app.services.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by kosa1010 on 09.06.16.
 */


@RestController
@RequestMapping("rest/cinemahall")
public class CinemaHallRestController {

    @Autowired
    private CinemaHallService cinemaHallService;

    @RequestMapping(value = "/all")
    public ResponseEntity<List<Cinemahall>> getAllMovies(Model model) {
        List<Cinemahall> cinemahall = cinemaHallService.findAll();
        return new ResponseEntity<List<Cinemahall>>(cinemahall, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Cinemahall> getDetailsOfCinema(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Cinemahall cinemahall = cinemaHallService.findOne(id.get());
            if (cinemahall != null) {
                return new ResponseEntity<Cinemahall>(cinemahall, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Cinemahall>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Cinemahall>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Void> processAddMovieForm(@RequestBody Cinemahall cinemahall) {
        cinemaHallService.save(cinemahall);

        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
