package com.app.controllers;

import com.app.models.Cinema;
import com.app.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Marcin on 21.04.2016.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = {"/", "/home"})
    public String Index(Map<String, Object> modelMap){
        Cinema cinema = new Cinema();
        modelMap.put("cinema", cinema);
        modelMap.put("cinemas", cinemaService.findAll());
        return "index";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.POST)
    public ModelAndView searchShowings(@ModelAttribute("cinema") Cinema cinema, BindingResult result){
        Long idCinema = Long.parseLong(result.getFieldValue("id").toString());
        return new ModelAndView("redirect:/showing/cinema/" + idCinema);
    }

    @InitBinder("showing")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("id");
    }
}
