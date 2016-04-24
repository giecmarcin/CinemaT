package com.app.repositories;

import com.app.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 24.04.2016.
 */
public interface CinemaRepository extends JpaRepository<Cinema,Serializable> {
}
