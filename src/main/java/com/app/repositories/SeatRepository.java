package com.app.repositories;

import com.app.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 28.04.2016.
 */
public interface SeatRepository extends JpaRepository<Seat, Serializable> {
}
