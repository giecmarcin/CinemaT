package com.app.services;

import com.app.models.User;

public interface UserService extends GenericService<User> {
    User findUserByEmail(String email);

    User findUserByName(String name);

    User login(String email, String password);

    User getCurrentUserFromContext();

}
