package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
