package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.MovieDTO;
import com.cinema.galaxy.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    public Page<MovieDTO> getMoviesByGenre(String genre, Pageable pageable);
    public MovieDTO getMovieById(Long movieId);
    public MovieDTO addMovie(MovieDTO movieDTO);
    public boolean deleteMovie(Long movieId);
    public MovieDTO updateMovie(Long id, MovieDTO updatedMovie);
}
