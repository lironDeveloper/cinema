package com.cinema.galaxy.DTOs.Showtime;

import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowtimeCreationDTO {
    private Long id;
    @NotNull(message = "יש לציין מזהה סרט.")
    private Long movieId;
    @NotNull(message = "יש לציין מזהה אולם.")
    private Long hallId;
    @Future(message = "תאריך תחילת הקרנה חייב להיות בעתיד.")
    @NotNull(message = "יש לציין תאריך ושעת תחילת הקרנה")
    private LocalDateTime startTime;
}
