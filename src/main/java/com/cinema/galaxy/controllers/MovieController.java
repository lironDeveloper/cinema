package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Movie.MovieCreationDTO;
import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import com.cinema.galaxy.DTOs.Movie.MovieUpdateDTO;
import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.services.MovieServiceImpl;
import com.cinema.galaxy.services.ReviewServiceImpl;
import com.cinema.galaxy.validators.enumValidator.ValidEnumValue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/movie")
@Validated
@RequiredArgsConstructor
@CrossOrigin
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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MovieDTO> addMovie(@Valid @RequestBody MovieCreationDTO movieCreationDTO) {
        MovieDTO addedMovie = movieServiceImpl.addMovie(movieCreationDTO);
        return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieServiceImpl.deleteMovie(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieUpdateDTO movieUpdateDTO) {
        MovieDTO movie = movieServiceImpl.updateMovie(id, movieUpdateDTO);
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

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchMovies(@RequestParam String keyword) {
        List<MovieDTO> searchResults = movieServiceImpl.searchMovies(keyword);
        return ResponseEntity.ok(searchResults);
    }

    @PostMapping("/thumbnail/upload/{movieId}")
    public ResponseEntity<String> thumbnailUploading(@RequestParam("file") MultipartFile file, @PathVariable Long movieId) throws Exception {
        if(movieServiceImpl.saveThumbnail(file, movieId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/thumbnail/{movieId}")
    public ResponseEntity<byte[]> getThumbnailById(@PathVariable Long movieId) {
        byte[] thumbnail = movieServiceImpl.getThumbnailByMovieId(movieId);
        if(thumbnail != null){
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(thumbnail);
        }
        return ResponseEntity.notFound().build();
    }
}
