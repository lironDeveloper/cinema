package com.cinema.galaxy.DTOs.Seat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeatDetailsDTO {
    @NotNull(message = "יש לציין שורת מושב.")
    private Integer rowNum;
    @NotNull(message = "יש לציין עמודת מושב.")
    private Integer colNum;
}
