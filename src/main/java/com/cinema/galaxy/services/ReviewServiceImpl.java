package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Review.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.models.Review;
import com.cinema.galaxy.models.User;
import com.cinema.galaxy.repositories.MovieRepository;
import com.cinema.galaxy.repositories.ReviewRepository;
import com.cinema.galaxy.repositories.UserRepository;
import com.cinema.galaxy.serviceInterfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ReviewDTO> getReviewsByMovieId(Long movieId, Pageable pageable){
        Page<Review> reviews = reviewRepository.findByMovieIdOrderByCreatedOnDesc(movieId, pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public Page<ReviewDTO> getReviewsByUserId(Long userId, Pageable pageable){
        Page<Review> reviews = reviewRepository.findByUserId(userId, pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public ReviewDTO addReview(ReviewCreationDTO reviewCreationDTO, String email){
        // Check if the user exists in the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("לא קיים משתמש כזה."));

        // Check if the movie with the provided movieId exists in the database
        Movie movie = movieRepository.findById(reviewCreationDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיים סרט עם המזהה הזה."));

        // Create a new Review entity from the ReviewDTO
        Review review = modelMapper.map(reviewCreationDTO, Review.class);

        // Set the User and Movie entities for the review
        review.setUser(user);
        review.setMovie(movie);

        // Save the review in the database
        Review addedReview = reviewRepository.save(review);

        // Map the addedReview back to ReviewDTO and return it
        return modelMapper.map(addedReview, ReviewDTO.class);
    }
}
