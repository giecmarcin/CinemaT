package com.app.controllers;

import com.app.models.Cinema;
import com.app.models.CinemaHall;
import com.app.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hall")
public class CinemaHallController {

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = {"/add/{idCinema}"})
    public ModelAndView getAddMovieForm(Model model, @PathVariable Long idCinema) {
        CinemaHall cinemaHall = new CinemaHall();
        model.addAttribute("cinemaHall", cinemaHall);
        return new ModelAndView("/cinema/addHall");

    }

    @RequestMapping(value = {"/add/{idCinema}"}, method = RequestMethod.POST)
    public ModelAndView processAddMovieForm(@ModelAttribute("cinemaHall") CinemaHall cinemaHall, @PathVariable Long idCinema) {
        Cinema cinema = cinemaService.findOne(idCinema);
        if (cinema != null) {
            cinemaService.addCinemaHall(cinemaHall, cinema.getId());
        }
        return new ModelAndView("redirect:/cinema/all");
    }
}

