package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.Movie.MovieCreationDTO;
import com.cinema.galaxy.DTOs.Movie.MovieDTO;
import com.cinema.galaxy.DTOs.Movie.MovieUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    public Page<MovieDTO> getMoviesByGenre(String genre, Pageable pageable);
    public MovieDTO getMovieById(Long movieId);
    public MovieDTO addMovie(MovieCreationDTO movieCreationDTO);
    public boolean deleteMovie(Long movieId);
    public MovieDTO updateMovie(Long id, MovieUpdateDTO movieUpdateDTO);
    public List<MovieDTO> searchMovies(String keyword);
    public boolean saveThumbnail(MultipartFile file, Long id) throws Exception;
    public byte[] getThumbnailByMovieId(Long id);
}
