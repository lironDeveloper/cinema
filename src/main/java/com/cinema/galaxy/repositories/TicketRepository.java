package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByShowtimeId(Long showtimeId);
    Page<Ticket> findAllByUserId(Long userId, Pageable pageable);
    boolean existsByShowtimeIdAndSeatId(Long showtimeId, Long seatId);
}
