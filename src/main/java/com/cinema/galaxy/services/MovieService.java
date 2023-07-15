package com.cinema.galaxy.services;

import com.cinema.galaxy.enums.Genre;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Page<Movie> getMoviesByGenre(String genre, Pageable pageable){
        return movieRepository.findByGenre(genre, pageable);
    }

    public Movie getMovie(Long movieId){
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.orElse(null);
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public boolean deleteMovie(Long movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        }

        return false;
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setDuration(updatedMovie.getDuration());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setGenre(updatedMovie.getGenre());
            movie.setDirector(updatedMovie.getDirector());
            movie.setLanguage(updatedMovie.getLanguage());
            movie.setMinAge(updatedMovie.getMinAge());
            return movieRepository.save(movie);
        }
        return null;
    }

}
