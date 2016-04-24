package com.app.services.impl;

import com.app.models.Role;
import com.app.models.User;
import com.app.services.RoleService;
import com.app.services.UserService;
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
    RoleService roleService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init(){
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

    }
}
