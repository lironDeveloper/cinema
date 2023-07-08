package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
