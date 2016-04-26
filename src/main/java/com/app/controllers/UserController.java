package com.app.controllers;

import com.app.models.Movie;
import com.app.models.User;
import com.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/account/login")
    public String getLoginPage() {
        return "/user/login";
    }

    @RequestMapping(value = {"/add", "/add/{id}"})
    public ModelAndView getAddMovieForm(Model model, @PathVariable Optional<Long> id) {
        User user = null;
        if (id.isPresent()) {
            user = userService.findOne(id.get());
            if (user != null) {
                model.addAttribute("user", user);
                return new ModelAndView("/user/register");
            } else {
                return new ModelAndView("redirect:/");
            }
        }
        user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("/user/register");
    }

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.POST)
    public ModelAndView processAddMovieForm(@ModelAttribute("user") User user, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            User newUser = userService.findOne(id.get());
            if (newUser != null) {
                newUser = user;
                userService.save(user);
            } else {
                //Not Found

            }
        } else {

            userService.save(user);
        }
        return new ModelAndView("redirect:/");
    }


    @RequestMapping(value = "/all")
    public String getAllMovies(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/user/index";
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView getDetailsOfMovie(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            User user = userService.findOne(id.get());
            if (user != null) {
                model.addAttribute("user", user);
                return new ModelAndView("/user/details");
            }
        }
        return new ModelAndView("redirect:/users/all");
    }

    @RequestMapping(value = {"/confirm/{id}"})
    public ModelAndView getConfirmTheDelationPage(Model model, @PathVariable Long id) {
        User user = userService.findOne(id);
        if (user != null) {
            model.addAttribute("user", user);
            return new ModelAndView("/user/confirm");
        }
        return new ModelAndView("redirect:/user/all");
    }

    @RequestMapping(value = {"/remove/{id}"})
    public ModelAndView removeMovie(@PathVariable Long id) {
        User user = userService.findOne(id);
        userService.delete(user);
        return new ModelAndView("redirect:/movie/all");
    }

    @RequestMapping(value="/account/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/user/account/login");//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @InitBinder("user")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "name", "email", "password", "dob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
}
