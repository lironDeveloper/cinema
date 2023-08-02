package com.cinema.galaxy.DTOs.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private LocalDateTime releaseDate;
    private String genre;
    private String director;
    private String language;
    private Integer minAge;
    private Instant createdOn;
}
