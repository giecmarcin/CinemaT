package com.app.repositories;

import com.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 22.04.2016.
 */
public interface UserRepository extends JpaRepository<User, Serializable> {
    User findUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
