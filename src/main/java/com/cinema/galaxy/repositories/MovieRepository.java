package com.cinema.galaxy.repositories;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT DISTINCT m FROM Movie m " +
            "INNER JOIN Showtime s ON m.id = s.movie.id " +
            "WHERE m.genre = :genre AND s.startTime > :currentDateTime " +
            "ORDER BY m.releaseDate")
    Page<Movie> findByGenreAndFutureShowtime(String genre, Instant currentDateTime, Pageable pageable);

    List<Movie> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrDirectorContainingIgnoreCase(
            String titleKeyword, String descriptionKeyword, String directorNameKeyword);
}
