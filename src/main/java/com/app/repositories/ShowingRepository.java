package com.app.repositories;

import com.app.models.Cinema;
import com.app.models.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcin on 19.05.2016.
 */
public interface ShowingRepository extends JpaRepository<Showing,Serializable> {
    List<Showing> findByCinema(Cinema cinema);

    List<Showing> findByIsActive(boolean isActive);
}
