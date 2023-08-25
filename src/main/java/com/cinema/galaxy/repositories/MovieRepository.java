package com.cinema.galaxy.repositories;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByGenreOrderByReleaseDate(String genre, Pageable pageable);
    List<Movie> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrDirectorContainingIgnoreCase(
            String titleKeyword, String descriptionKeyword, String directorNameKeyword);
}
