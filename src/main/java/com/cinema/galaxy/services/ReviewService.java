package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Review;
import com.cinema.galaxy.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Page<Review> getReviewsByMovieId(Long movieId, Pageable pageable){
        return reviewRepository.findByMovieId(movieId, pageable);
    }

    public Page<Review> getReviewsByUserId(Long userId, Pageable pageable){
        return reviewRepository.findByUserId(userId, pageable);
    }

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
