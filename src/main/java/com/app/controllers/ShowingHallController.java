package com.app.controllers;

import com.app.models.Cinema;
import com.app.models.Cinemahall;
import com.app.models.Movie;
import com.app.models.Showing;
import com.app.services.CinemaHallService;
import com.app.services.CinemaService;
import com.app.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/showing/hall")
public class ShowingHallController {

    @Autowired
    CinemaHallService cinemaHallService;

    @Autowired
    private ShowingService showingService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = {"/{id}"})
    public ModelAndView getChoosenHallForm(Map<String, Object> modelMap, @PathVariable Optional<Long> id) {
        if(id.isPresent()){
            Showing showing = showingService.findOne(id.get());
            if (showing != null) {
                Cinema cinema = cinemaService.findOne(showing.getCinema().getId());
                modelMap.put("showing", showing);
                for(Cinemahall ch : cinema.getCinemahalls()){
                    System.out.println(ch.getId() + " " + ch.getName());
                }
                modelMap.put("halls", cinema.getCinemahalls());
                return new ModelAndView("/showing/hall");
            }
        }
        return new ModelAndView("/showing/add");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postChoosenForm(@ModelAttribute("showing") Showing showing, BindingResult result) {
        System.out.println(showing.getId());
        Showing showingToUpdate = showingService.findOne(showing.getId());
        Long idCinemaHall = Long.parseLong(result.getFieldValue("cinemahall").toString());
        Cinemahall cinemahall = cinemaHallService.findOne(idCinemaHall);
        showingToUpdate.setCinemahall(cinemahall);
        showingService.save(showingToUpdate);
        return new ModelAndView("/showing/add");
    }

    @InitBinder("showing")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "cinemahall");
    }
}
