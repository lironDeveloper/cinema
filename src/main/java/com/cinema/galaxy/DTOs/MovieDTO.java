package com.cinema.galaxy.DTOs;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.enums.Language;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

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
