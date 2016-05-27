package com.app.repositories;

import com.app.models.Booking;
import com.app.models.Showing;
import com.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Serializable> {
    List<Booking> findByShowing(Showing showing);

    List<Booking> findByUser(User user);

    List<Booking> findByIsActive(boolean isActive);
}
