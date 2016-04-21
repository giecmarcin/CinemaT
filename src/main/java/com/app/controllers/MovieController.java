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

/**
 * Created by Marcin on 21.04.2016.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/add")
    public String getAddMovieForm(Model model, @RequestParam(value = "id", required = false) Long id){
        Movie movie = null;
        if (id == null) {
            movie = new Movie();
        } else {
            movie = movieService.findOne(id);
        }
        model.addAttribute("movie", movie);
        return "/movie/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView processAddMovieForm(@ModelAttribute("movie") Movie movie){
        if(movie.getId()==null){
            movieService.save(movie);
        }else{
            Movie newMovie = movieService.findOne(movie.getId());
            newMovie = movie;
            movieService.save(movie);
        }
        return new ModelAndView("redirect:/movies/all");
    }

    @RequestMapping(value = "/all")
    public String getAllMovies(Model model){
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "/movie/index";
    }

    @InitBinder("movie")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("title", "description", "publicationDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
}
