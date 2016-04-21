package com.app.repositories;

import com.app.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface MovieRepository extends JpaRepository<Movie,Serializable> {
}
