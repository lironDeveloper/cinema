package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.Seat.SeatDetailsDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeCreationDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeUpdateDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketDetailsDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketReservationDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketSeatDetailsDTO;
import com.cinema.galaxy.models.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    public TicketDetailsDTO reserveTicket(TicketReservationDTO ticketReservationDTO, String email);
    public Page<TicketDetailsDTO> getTicketsByUser(String email, Pageable page);
    public List<TicketSeatDetailsDTO> getTicketsByShowtimeId(Long showtimeId);
    public boolean deleteReservation(Long ticketId, String email);
}
