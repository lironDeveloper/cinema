package com.cinema.galaxy.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCreationDTO {
    private Long id;
    private Long userId;
    private Long movieId;
    private int rating;
    private String comment;
}
