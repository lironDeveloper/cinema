package com.cinema.galaxy.DTOs.Ticket;

import com.cinema.galaxy.DTOs.Seat.SeatDetailsDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TicketSeatDetailsDTO {
    private Integer seatRowNum;
    private Integer seatColNum;
}
