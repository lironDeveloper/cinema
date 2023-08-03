package com.cinema.galaxy.DTOs.Ticket;

import com.cinema.galaxy.DTOs.Seat.SeatDetailsDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import com.cinema.galaxy.models.Seat;
import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TicketDetailsDTO {
    private Long id;
    private UserDTO user;
    private ShowtimeDTO showtime;
    private SeatDetailsDTO seat;
    private Instant createdOn;
}
