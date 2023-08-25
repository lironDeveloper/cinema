package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.DTOs.Seat.SeatDetailsDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketDetailsDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketReservationDTO;
import com.cinema.galaxy.DTOs.Ticket.TicketSeatDetailsDTO;
import com.cinema.galaxy.exceptions.UniqueException;
import com.cinema.galaxy.models.*;
import com.cinema.galaxy.repositories.SeatRepository;
import com.cinema.galaxy.repositories.ShowtimeRepository;
import com.cinema.galaxy.repositories.TicketRepository;
import com.cinema.galaxy.repositories.UserRepository;
import com.cinema.galaxy.serviceInterfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ModelMapper modelMapper;

    @Override
    public TicketDetailsDTO reserveTicket(TicketReservationDTO ticketReservationDTO, String email) {
        // Check if the user exists in the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("לא קיים משתמש כזה."));

        // Check if the showtime with the provided showtimeId exists in the database
        Showtime showtime = showtimeRepository.findById(ticketReservationDTO.getShowtimeId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיימת הקרנה עם המזהה הזה."));

        // Check whether the seat with the provided details exists and is not already occupied
        Seat seat = seatRepository.findSeatByColNumAndRowNumAndHallId(ticketReservationDTO.getSeat().getColNum(), ticketReservationDTO.getSeat().getRowNum(), showtime.getHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיים מושב עם פרטים אלו באולם זה."));

        if(ticketRepository.existsByShowtimeIdAndSeatId(showtime.getId(), seat.getId())){
            throw new IllegalArgumentException("מושב זה כבר תפוס בהקרנה זו, אנא נסו מושב אחר.");
        }

        // Create a new ticker entity
        Ticket ticket = modelMapper.map(ticketReservationDTO, Ticket.class);

        // Set the User, Showtime and Seat entities for the ticket
        ticket.setUser(user);
        ticket.setShowtime(showtime);
        ticket.setSeat(seat);

        try {
            // Save the ticket in the database
            Ticket addedTicket = ticketRepository.save(ticket);
            return modelMapper.map(addedTicket, TicketDetailsDTO.class);
        } catch (Exception exception){
            throw new UniqueException("כרטיס במושב זה בהקרנה הזו כבר קיים.");
        }
    }

    @Override
    public Page<TicketDetailsDTO> getTicketsByUser(String email, Pageable page) {
        // Check if the user exists in the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("לא קיים משתמש כזה."));

        Page<Ticket> tickets = ticketRepository.findAllByUserId(user.getId(), page);
        return tickets.map(ticket -> modelMapper.map(ticket, TicketDetailsDTO.class));
    }

    @Override
    public List<TicketSeatDetailsDTO> getTicketsByShowtimeId(Long showtimeId) {
        List<Ticket> tickets = ticketRepository.findAllByShowtimeId(showtimeId);
        return tickets.stream().map(ticket -> modelMapper.map(ticket, TicketSeatDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteReservation(Long ticketId, String email) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            // Check if the user exists in the database
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("לא קיים משתמש כזה."));

            // Check whether the ticket belongs to the current authenticated user
            if(ticket.getUser().getId().equals(user.getId())){
                ticketRepository.deleteById(ticketId);
                return true;
            }
        }
        return false;
    }
}
