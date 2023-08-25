package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Review.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.services.ReviewServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/review")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class ReviewController {
    private final ReviewServiceImpl reviewServiceImpl;

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@Valid @RequestBody ReviewCreationDTO reviewCreationDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ReviewDTO addedReview = reviewServiceImpl.addReview(reviewCreationDTO, email);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }
}
