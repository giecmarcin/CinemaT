package com.app.controllers;

/**
 * Created by kosa1010 on 08.06.16.
 */

import com.app.models.User;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers(Model model) {
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}")
    public ResponseEntity<User> getDetailsOfUsersName(Model model, @PathVariable Optional<String> name) {
        if (name.isPresent()) {
            User user = userService.findUserByName(name.get());
            if (user != null) {
                return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/id/{id}")
    public ResponseEntity<User> getDetailsOfUsers(Model model, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            User user = userService.findOne(id.get());
            if (user != null) {
                return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ResponseEntity<Void> processAddMovieForm(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
