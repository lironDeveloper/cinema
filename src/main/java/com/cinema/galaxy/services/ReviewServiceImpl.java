package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.ReviewCreationDTO;
import com.cinema.galaxy.DTOs.ReviewDTO;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.models.Review;
import com.cinema.galaxy.models.User;
import com.cinema.galaxy.repositories.MovieRepository;
import com.cinema.galaxy.repositories.ReviewRepository;
import com.cinema.galaxy.repositories.UserRepository;
import com.cinema.galaxy.serviceInterfaces.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ReviewDTO> getReviewsByMovieId(Long movieId, Pageable pageable){
        Page<Review> reviews = reviewRepository.findByMovieId(movieId, pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public Page<ReviewDTO> getReviewsByUserId(Long userId, Pageable pageable){
        Page<Review> reviews = reviewRepository.findByUserId(userId, pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public ReviewDTO addReview(ReviewCreationDTO reviewCreationDTO){
        // Check if the user with the provided userId exists in the database
        User user = userRepository.findById(reviewCreationDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיים משתמש עם המזהה הזה."));

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