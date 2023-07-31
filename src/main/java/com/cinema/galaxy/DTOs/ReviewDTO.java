package com.cinema.galaxy.DTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
    private String userUsername;
    private String movieTitle;
    private int rating;
    private String comment;
    private Instant createdOn;
}