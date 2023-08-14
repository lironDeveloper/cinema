package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketDetailsDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketReservationDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketSeatDetailsDTO;
import com.cinema.galaxy.models.Ticket;
import com.cinema.galaxy.services.TicketServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/ticket")
@RequiredArgsConstructor
@CrossOrigin
public class TicketController {
    private final TicketServiceImpl ticketServiceImpl;

    @PostMapping
    public ResponseEntity<TicketDetailsDTO> reserveTicket(@Valid @RequestBody TicketReservationDTO ticketReservationDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        TicketDetailsDTO addedTicket = ticketServiceImpl.reserveTicket(ticketReservationDTO, email);
        return new ResponseEntity<>(addedTicket, HttpStatus.CREATED);
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketDetailsDTO>> getAllUsersTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Pageable pageable = PageRequest.of(page, size);
        Page<TicketDetailsDTO> tickets = ticketServiceImpl.getTicketsByUser(email, pageable);
        return ResponseEntity.ok(tickets.getContent());
    }

    @GetMapping("/showtime/{id}")
    public ResponseEntity<List<TicketSeatDetailsDTO>> getTicketsByShowtimeId(@PathVariable("id") Long id) {
        List<TicketSeatDetailsDTO> tickets = ticketServiceImpl.getTicketsByShowtimeId(id);
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ticketServiceImpl.deleteReservation(id, email)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
