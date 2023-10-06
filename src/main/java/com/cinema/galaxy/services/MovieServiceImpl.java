package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Branch.BranchDTO;
import com.cinema.galaxy.DTOs.Movie.MovieCreationDTO;
import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import com.cinema.galaxy.DTOs.Movie.MovieUpdateDTO;
import com.cinema.galaxy.exceptions.UniqueException;
import com.cinema.galaxy.models.Branch;
import com.cinema.galaxy.models.Movie;
import com.cinema.galaxy.models.MovieThumbnail;
import com.cinema.galaxy.repositories.MovieRepository;
import com.cinema.galaxy.serviceInterfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MovieDTO> getMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movie -> modelMapper.map(movie, MovieDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Page<MovieDTO> getMoviesByGenre(String genre, Pageable pageable){
        Page<Movie> movies = movieRepository.findByGenreAndFutureShowtime(genre, LocalDateTime.now(), pageable);
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
        try {
            Movie addedMovie = movieRepository.save(movie);
            return modelMapper.map(addedMovie, MovieDTO.class);
        } catch (Exception exception){
            throw new UniqueException("סרט זה כבר קיים.");
        }
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
            try {
                Movie editedMovie = movieRepository.save(movie);
                return modelMapper.map(editedMovie, MovieDTO.class);
            } catch (Exception exception){
                throw new UniqueException("סרט זה כבר קיים.");
            }
        }
        return null;
    }

    @Override
    public List<MovieDTO> searchMovies(String keyword) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrDirectorContainingIgnoreCase(keyword, keyword, keyword);
        return movies.stream().map(movie -> modelMapper.map(movie, MovieDTO.class)).collect(Collectors.toList());
    }

    @Override
    public boolean saveThumbnail(MultipartFile file, Long id) throws Exception {
        final int MAX_FILE_SIZE = 1024 * 1024; // 1MB
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Set<String> validContentTypes = new HashSet<>(List.of(
                    MediaType.IMAGE_PNG_VALUE
            ));

            if (!validContentTypes.contains(file.getContentType())) {
                throw new IllegalArgumentException("פורמט הקובץ שהועלה אינו נתמך. יש להעלות קבצי PNG בלבד.");
            }

            if (file.getBytes().length > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("התמונה שהועלתה חורגת מהגודל המקסימלי.");
            }

            Movie movie = movieOptional.get();
            MovieThumbnail thumbnail = Optional.ofNullable(movie.getMovieThumbnail()).orElse(new MovieThumbnail());
            thumbnail.setThumbnail(file.getBytes());
            movie.setMovieThumbnail(thumbnail);
            movieRepository.save(movie);
            return true;
        }
        return false;
    }

    @Override
    public byte[] getThumbnailByMovieId(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.map(movie -> movie.getMovieThumbnail().getThumbnail()).orElse(null);
    }
}
