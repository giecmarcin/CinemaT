package com.app.repositories;

import com.app.models.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 24.04.2016.
 */
public interface CinemaHallRepository extends JpaRepository<CinemaHall, Serializable> {
}
