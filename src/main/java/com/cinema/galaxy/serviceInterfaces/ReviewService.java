package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.Review.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    public Page<ReviewDTO> getReviewsByMovieId(Long movieId, Pageable pageable);
    public Page<ReviewDTO> getReviewsByUserId(Long userId, Pageable pageable);
    public ReviewDTO addReview(ReviewCreationDTO reviewCreationDTO, String email);
}
