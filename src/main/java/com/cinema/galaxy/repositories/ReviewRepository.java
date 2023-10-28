package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByMovieIdOrderByCreatedOnDesc(Long movie_id, Pageable pageable);
    Page<Review> findByUserId(Long user_id, Pageable pageable);

}

