package com.app.controllers;

import com.app.models.Cinema;
import com.app.models.Cinemahall;
import com.app.models.dto.CinemaAndHall;
import com.app.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = {"/add", "/add/{id}"})
    public ModelAndView getAddCinemaForm(Model model, @PathVariable Optional<Long> id) {
        Cinema cinema = null;
        if (id.isPresent()) {
            cinema = cinemaService.findOne(id.get());
            if (cinema != null) {
                model.addAttribute("cinema", cinema);
                return new ModelAndView("/cinema/add");
            } else {
                return new ModelAndView("redirect:/cinema/all");
            }
        }
        cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return new ModelAndView("/cinema/add");
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView processAddCinemaForm(@ModelAttribute("cinema") Cinema cinema) {
        if(cinema.getId()!=null){
            Cinema newCinema = cinemaService.findOne(cinema.getId());
            newCinema = cinema;
            cinemaService.save(cinema);
        }else{
            cinemaService.save(cinema);
        }
        return new ModelAndView("redirect:/cinema/all");
    }


    @RequestMapping(value = "/all")
    public ModelAndView getAllCinemas(Model model) {
        List<Cinema> cinemas = cinemaService.findAll();
        if (cinemas != null) {
            model.addAttribute("cinemas", cinemas);
            return new ModelAndView("/cinema/index");
        }
        return new ModelAndView("redirect/");
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView getDetailsOfMovie(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Cinema cinema = cinemaService.findOne(id.get());
            if (cinema != null) {
                model.addAttribute("cinema", cinema);
                return new ModelAndView("/cinema/details");
            }
        }
        return new ModelAndView("redirect:/cinema/all");
    }

    @RequestMapping(value = {"/confirm/{id}"})
    public ModelAndView getConfirmTheDelationPage(Model model, @PathVariable Long id) {
        Cinema cinema = cinemaService.findOne(id);
        if (cinema != null) {
            model.addAttribute("cinema", cinema);
            return new ModelAndView("/cinema/confirm");
        }
        return new ModelAndView("redirect:/cinema/all");
    }

    @RequestMapping(value = {"/remove/{id}"})
    public ModelAndView removeMovie(@PathVariable Long id) {
        Cinema cinema = cinemaService.findOne(id);
        cinemaService.delete(cinema);
        return new ModelAndView("redirect:/cinema/all");
    }

    @RequestMapping(value = {"/hall", "/hall/{id}"})
    public ModelAndView getAddCinemaHallForm(Model model, @PathVariable Optional<Long> id) {
        Cinemahall cinemahall = new Cinemahall();
        CinemaAndHall cinemaAndHall = new CinemaAndHall();
        Cinema cinema = cinemaService.findOne(id.get());
        if(cinema!=null){
            cinemaAndHall.setCinema(cinema);
            cinemaAndHall.setCinemahall(cinemahall);
            if (cinemaAndHall.getCinemahall() != null) {
                int numberOfCinemaHalls = cinema.getCinemahalls().size();
                cinemaAndHall.getCinemahall().setName("Sala " + (numberOfCinemaHalls + 1));
            }

            cinemaAndHall.getCinemahall().setNumberOfSeats(50);
            model.addAttribute("cAh", cinemaAndHall);
            return new ModelAndView("/cinema/addHall");
        }else{
            return new ModelAndView("/cinema/all");
        }
    }

    @RequestMapping(value = {"/hall", "/hall/{id}"}, method = RequestMethod.POST)
    public ModelAndView processAddCinemaHallForm(@ModelAttribute("cAh") CinemaAndHall cinemaAndHall) {
        cinemaService.addCinemaHall(cinemaAndHall.getCinemahall(), cinemaAndHall.getCinema().getId());
        return new ModelAndView("redirect:/cinema/all");
    }


//    @InitBinder("cinema")
//    public void initialiseBinder(WebDataBinder binder) {
//        //binder.setAllowedFields("id", "title", "description", "publicationDate");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        //Register it as custom editor for the Date type
//        binder.registerCustomEditor(Date.class, editor);
//    }

}
