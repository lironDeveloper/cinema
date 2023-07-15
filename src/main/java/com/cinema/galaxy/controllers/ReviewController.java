package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.models.Review;
import com.cinema.galaxy.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/movie/{movieId}") //TODO: should be probably /api/movie/{id}/reviews
    public ResponseEntity<Page<Review>> getReviewsByMovieId(
            @PathVariable Long movieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewService.getReviewsByMovieId(movieId, pageable);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{userId}") //TODO: should be probably /api/user/{id}/reviews
    public ResponseEntity<Page<Review>> getReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewService.getReviewsByUserId(userId, pageable);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review) { //TODO: userid should be inferred from the context.
        Review addedReview = reviewService.addReview(review);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }
}
