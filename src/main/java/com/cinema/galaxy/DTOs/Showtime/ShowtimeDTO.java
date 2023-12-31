package com.cinema.galaxy.DTOs.Showtime;

import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ShowtimeDTO {
    private Long id;
    private MovieDTO movie;
    private HallDTO hall;
    private Instant startTime;
    private Instant endTime;
    private Instant createdOn;
}