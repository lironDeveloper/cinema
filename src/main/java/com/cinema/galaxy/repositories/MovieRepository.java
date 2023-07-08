package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
