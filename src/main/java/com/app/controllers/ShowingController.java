package com.app.controllers;

import com.app.models.Cinema;
import com.app.models.Movie;
import com.app.models.Showing;
import com.app.services.CinemaService;
import com.app.services.MovieService;
import com.app.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Marcin on 19.05.2016.
 */
@Controller
@RequestMapping("/showing")
public class ShowingController {

    @Autowired
    private ShowingService showingService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = {"/add", "/add/{id}"})
    public ModelAndView getAddCinemaForm(Map<String, Object> modelMap, @PathVariable Optional<Long> id) {
        List<Movie> movies = movieService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();

        modelMap.put("movies", movies);
        modelMap.put("cinemas", cinemas);

        Showing showing = null;
        if (id.isPresent()) {
            showing = showingService.findOne(id.get());
            if (showing != null) {
                modelMap.put("showing", showing);
                return new ModelAndView("/showing/add");
            } else {
                return new ModelAndView("redirect:/showing/all");
            }
        }
        showing = new Showing();
        modelMap.put("showing", showing);
        return new ModelAndView("/showing/add");
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView processAddCinemaForm(@Valid @ModelAttribute("showing") Showing showing, BindingResult result, HttpServletRequest request) {
        Long idOfMovie = Long.parseLong(result.getFieldValue("movie").toString());
        showing.setMovie(movieService.findOne(idOfMovie));
        Long idOfCinema = Long.parseLong(result.getFieldValue("cinema").toString());
        showing.setCinema(cinemaService.findOne(idOfCinema));

//        if (result.hasErrors()) {
//            System.out.println(result.getAllErrors());
//        }
        if (showing.getId() != null) {
            System.out.println(showing.getMovie());
            Showing newShowing = showingService.findOne(showing.getId());
            newShowing = showing;
            showingService.save(showing);
        } else {
            showingService.save(showing);
        }
        return new ModelAndView("redirect:/showing/hall/" + showing.getId());
    }


    @RequestMapping(value = {"/cinema/{id}"})
    public ModelAndView getAllShowingInCinema(Map<String, Object> modelMap, @PathVariable Optional<Long> id) {
        System.out.println("tto: " + id.get());
        if (id.isPresent()) {
            List<Showing> showings = showingService.findAllShowingInCinema(id.get());
           modelMap.put("showings", showings);
        }
        return new ModelAndView("/showing/allForUser");
    }

    @InitBinder("showing")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "movie", "cinema", "date", "time");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
}
