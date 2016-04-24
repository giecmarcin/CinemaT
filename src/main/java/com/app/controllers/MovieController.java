package com.app.controllers;

import com.app.models.Movie;
import com.app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marcin on 21.04.2016.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = {"/add", "/add/{id}"})
    public ModelAndView getAddMovieForm(Model model, @PathVariable Optional<Long> id) {
        Movie movie = null;
        if (id.isPresent()) {
            movie = movieService.findOne(id.get());
            if (movie != null) {
                model.addAttribute("movie", movie);
                return new ModelAndView("/movie/add");
            } else {
                return new ModelAndView("redirect:/movie/all");
            }
        }
        movie = new Movie();
        model.addAttribute("movie", movie);
        return new ModelAndView("/movie/add");
    }

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.POST)
    public ModelAndView processAddMovieForm(@ModelAttribute("movie") Movie movie, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Movie newMovie = movieService.findOne(id.get());
            if (newMovie != null) {
                newMovie = movie;
                movieService.save(movie);
            } else {
                //Not Found
            }
        } else {
            movieService.save(movie);
        }
        return new ModelAndView("redirect:/movie/all");
    }


    @RequestMapping(value = "/all")
    public ModelAndView getAllMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        if(movies!=null){
            model.addAttribute("movies", movies);
            return new ModelAndView("/movie/index");
        }
        return new ModelAndView("redirect/");
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView getDetailsOfMovie(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Movie movie = movieService.findOne(id.get());
            if (movie != null) {
                model.addAttribute("movie", movie);
                return new ModelAndView("/movie/details");
            }
        }
        return new ModelAndView("redirect:/movie/all");
    }

    @RequestMapping(value = {"/confirm/{id}"})
    public ModelAndView getConfirmTheDelationPage(Model model, @PathVariable Long id) {
        Movie movie = movieService.findOne(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            return new ModelAndView("/movie/confirm");
        }
        return new ModelAndView("redirect:/movie/all");
    }

    @RequestMapping(value = {"/remove/{id}"})
    public ModelAndView removeMovie(@PathVariable Long id) {
        Movie movie = movieService.findOne(id);
        movieService.delete(movie);
        return new ModelAndView("redirect:/movie/all");
    }

    @InitBinder("movie")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "title", "description", "publicationDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
}
