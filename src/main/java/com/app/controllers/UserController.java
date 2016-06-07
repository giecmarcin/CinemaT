package com.app.controllers;

import com.app.models.Role;
import com.app.models.User;
import com.app.services.RoleService;
import com.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView processAddMovieForm(@ModelAttribute("user") User user) {
        if (user.getId() != null) {
            User newUser = userService.findOne(user.getId());
            newUser = user;
            userService.save(user);
        } else {
            Role role = roleService.findByName("ROLE_USER");
            List<Role> roles = new LinkedList<>();
            roles.add(role);
            user.setRoles(roles);
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


    @InitBinder("user")
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "name", "email", "password", "dob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
}
