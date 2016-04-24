package com.app.services.impl;

import com.app.models.Role;
import com.app.models.User;
import com.app.repositories.UserRepository;
import com.app.services.GenericService;
import com.app.services.RoleService;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Marcin on 22.04.2016.
 */

@Service
public class UserServiceImpl implements GenericService<User>, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Override
    public User save(User user) {
        Role role = roleService.findByName("User");
        Date currentDate = new Date();
        user.setDob(currentDate);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
