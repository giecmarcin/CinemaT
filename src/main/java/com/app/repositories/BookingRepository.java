package com.app.repositories;

import com.app.models.Booking;
import com.app.models.Cinema;
import com.app.models.Showing;
import com.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Serializable> {
    List<Booking> findByShowing(Showing showing);

    List<Booking> findByUser(User user);

    List<Booking> findByIsActive(boolean isActive);

    List<Booking> findByUserAndIsActive(User user, boolean isActive);

    List<Booking> findByCinema(Cinema cinema);
    List<Booking> findByCinemaAndIsActive(Cinema cinema, boolean isActive);
    List<Booking> findByCinemaAndIsActiveAndUser(Cinema cinema, boolean isActive, User user);
}
