package com.app.services.impl;

import com.app.models.*;
import com.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 24.04.2016.
 */
@Component
public class Init {

    @Autowired
    private MovieService movieService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    CinemaService cinemaService;

    @Autowired
    CinemaHallService cinemaHallService;

    @PostConstruct
    public void init() {
        Role roleUser = roleService.findByName("ROLE_USER");
        if (roleUser == null) {
            roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleService.save(roleUser);
        }


        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleService.save(roleAdmin);
        }

        User userAdmin = userService.findUserByEmail("giecmarcin@outlook.com");
        if (userAdmin == null) {
            userAdmin = new User();
            userAdmin.setName("admin");
            userAdmin.setDob(new Date());

            //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            //userAdmin.setPassword(encoder.encode("admin"));
            userAdmin.setEmail("giecmarcin@outlook.com");
            userAdmin.setPassword("test");
            List<Role> roles = new ArrayList<Role>();
            roles.add(roleAdmin);
            roles.add(roleUser);

            userAdmin.setRoles(roles);
            userService.save(userAdmin);
        }

        Cinema cinemaDemo = cinemaService.findOne(1L);
        if (cinemaDemo == null) {
            cinemaDemo = new Cinema();
            cinemaDemo.setName("Kino Demo 1");
            cinemaDemo.setCity("Mielec");
            cinemaDemo.setStreet("Cicha");
            cinemaDemo.setNumber(1);
            cinemaService.save(cinemaDemo);

            Cinemahall cinemahall = cinemaHallService.findOne(1L);
            if (cinemahall == null) {
                cinemahall = new Cinemahall();
                cinemahall.setName("Sala 1");
                cinemahall.setNumberOfSeats(50);
                cinemaHallService.save(cinemahall);

                cinemaDemo = cinemaService.findOne(1L);
                cinemaService.addCinemaHall(cinemahall, 1L);
                ;
            }
        }

        //Demo Cinema 2
        Cinema cinemaDemo2 = cinemaService.findOne(2L);
        if (cinemaDemo2 == null) {
            cinemaDemo2 = new Cinema();
            cinemaDemo2.setName("Kino Demo 2");
            cinemaDemo2.setCity("Mielec");
            cinemaDemo2.setStreet("Spokojna");
            cinemaDemo2.setNumber(1);
            cinemaService.save(cinemaDemo2);

            Cinemahall cinemahall = cinemaHallService.findOne(2L);
            if (cinemahall == null) {
                cinemahall = new Cinemahall();
                cinemahall.setName("Sala 1");
                cinemahall.setNumberOfSeats(50);
                cinemaHallService.save(cinemahall);

                cinemaDemo2 = cinemaService.findOne(2L);
                cinemaService.addCinemaHall(cinemahall, 2L);
            }
        }

        Movie movie = movieService.findOne(1L);
        if(movie==null){
            movie = new Movie();
            movie.setTitle("Deadpool");
            movie.setDescription("Film Science-Fiction");
            Date date = new Date();
            movie.setPublicationDate(date);
            movieService.save(movie);
        }

        Movie movie2 = movieService.findOne(2L);
        if(movie2==null){
            movie2 = new Movie();
            movie2.setTitle("Pitbull. Nowe porządki");
            movie2.setDescription("Film Dramat, Sensajca");
            Date date = new Date();
            movie2.setPublicationDate(date);
            movieService.save(movie2);
        }

    }
}
