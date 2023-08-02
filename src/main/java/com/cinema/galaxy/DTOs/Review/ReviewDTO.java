package com.cinema.galaxy.DTOs.Review;

import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private UserDTO user;
    private MovieDTO movie;
    private int rating;
    private String comment;
    private Instant createdOn;
}