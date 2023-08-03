package com.cinema.galaxy.DTOs.Ticket;

import com.cinema.galaxy.DTOs.Seat.SeatDetailsDTO;
import com.cinema.galaxy.models.Seat;
import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.models.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketReservationDTO {
    @NotNull(message = "יש לציין מזהה הקרנה.")
    private Long showtimeId;
    @NotNull(message = "יש לציין פרטי מושב.")
    private SeatDetailsDTO seat;
}
