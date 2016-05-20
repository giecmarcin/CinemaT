package com.app.repositories;

import com.app.models.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 19.05.2016.
 */
public interface ShowingRepository extends JpaRepository<Showing,Serializable> {
}