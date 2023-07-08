package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
