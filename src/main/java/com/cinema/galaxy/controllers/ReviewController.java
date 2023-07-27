package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.ReviewDTO;
import com.cinema.galaxy.services.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@Valid @RequestBody ReviewCreationDTO reviewCreationDTO) { //TODO: userid should be inferred from the context.
        ReviewDTO addedReview = reviewServiceImpl.addReview(reviewCreationDTO);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }
}
