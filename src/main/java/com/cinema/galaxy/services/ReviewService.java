package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Review;
import com.cinema.galaxy.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }
}
