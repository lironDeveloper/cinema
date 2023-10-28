package com.cinema.galaxy.DTOs.Showtime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ShowtimeUpdateDTO {
    private Long id;
    private Long movieId;
    private Long hallId;
    @Future(message = "תאריך תחילת הקרנה חייב להיות בעתיד.")
    private Instant startTime;
}
