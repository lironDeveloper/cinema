package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Movie.MovieCreationDTO;
import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import com.cinema.galaxy.DTOs.Movie.MovieUpdateDTO;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.repositories.MovieRepository;
import com.cinema.galaxy.serviceInterfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<MovieDTO> getMoviesByGenre(String genre, Pageable pageable){
        Page<Movie> movies = movieRepository.findByGenre(genre, pageable);
        return movies.map(movie -> modelMapper.map(movie, MovieDTO.class));
    }

    @Override
    public MovieDTO getMovieById(Long movieId){
        Optional<Movie> movie = movieRepository.findById(movieId);
        return modelMapper.map(movie.orElse(null), MovieDTO.class);
    }

    @Override
    public MovieDTO addMovie(MovieCreationDTO movieCreationDTO){
        Movie movie = modelMapper.map(movieCreationDTO, Movie.class);
        Movie addedMovie = movieRepository.save(movie);
        return modelMapper.map(addedMovie, MovieDTO.class);
    }

    @Override
    public boolean deleteMovie(Long movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        }

        return false;
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieUpdateDTO movieUpdateDTO) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            modelMapper.map(movieUpdateDTO, movie);
            Movie editedMovie = movieRepository.save(movie);
            return modelMapper.map(editedMovie, MovieDTO.class);
        }
        return null;
    }

}
