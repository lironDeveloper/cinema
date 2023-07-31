package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.MovieDTO;
import com.cinema.galaxy.DTOs.ReviewDTO;
import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.services.MovieServiceImpl;
import com.cinema.galaxy.services.ReviewServiceImpl;
import com.cinema.galaxy.validators.ValidEnumValue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/movie")
@Validated
@RequiredArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieServiceImpl;
    private final ReviewServiceImpl reviewServiceImpl;

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDTO>> getMoviesByGenre(@PathVariable("genre") @ValidEnumValue(enumClass = Genre.class, message = "יש לבחור זאנ'ר חוקי.") String genre,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieDTO> movies = movieServiceImpl.getMoviesByGenre(genre, pageable);
        return ResponseEntity.ok(movies.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long id) {
        MovieDTO movie = movieServiceImpl.getMovieById(id);
        if(movie != null) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        MovieDTO addedMovie = movieServiceImpl.addMovie(movieDTO);
        return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        if (movieServiceImpl.deleteMovie(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO updatedMovie) {
        MovieDTO movie = movieServiceImpl.updateMovie(id, updatedMovie);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMovieId(
            @PathVariable Long movieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewDTO> reviews = reviewServiceImpl.getReviewsByMovieId(movieId, pageable);
        return ResponseEntity.ok(reviews.getContent());
    }
}
