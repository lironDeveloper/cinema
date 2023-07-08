package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
