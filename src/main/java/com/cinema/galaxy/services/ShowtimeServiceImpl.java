package com.cinema.galaxy.services;

import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeCreationDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeUpdateDTO;
import com.cinema.galaxy.models.*;
import com.cinema.galaxy.repositories.BranchRepository;
import com.cinema.galaxy.repositories.HallRepository;
import com.cinema.galaxy.repositories.MovieRepository;
import com.cinema.galaxy.repositories.ShowtimeRepository;
import com.cinema.galaxy.serviceInterfaces.ShowtimeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final BranchRepository branchRepository;
    private final HallRepository hallRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ShowtimeDTO> getShowtimeByMovieIdAndBranchId(Long movieId, Long branchId, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable) {
        Page<Showtime> showtimes = showtimeRepository.findByMovieIdAndHall_BranchIdAndStartTimeBetween(movieId, branchId, fromDate, toDate, pageable);
        return showtimes.map(showtime -> modelMapper.map(showtime, ShowtimeDTO.class));
    }

    @Override
    public ShowtimeDTO addShowtime(ShowtimeCreationDTO showtimeCreationDTO) {
        // Check if the hall with the provided hallId exists in the database
        Hall hall = hallRepository.findById(showtimeCreationDTO.getHallId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיים אולם עם המזהה הזה."));

        // Check if the movie with the provided movieId exists in the database
        Movie movie = movieRepository.findById(showtimeCreationDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("לא קיים סרט עם המזהה הזה."));

        Showtime showtime = modelMapper.map(showtimeCreationDTO, Showtime.class);
        showtime.setHall(hall);
        showtime.setMovie(movie);

        // Save the showtime to the database
        Showtime savedShowtime = showtimeRepository.save(showtime);

        return modelMapper.map(savedShowtime, ShowtimeDTO.class);
    }

    @Override
    public ShowtimeDTO getShowtimeById(Long showtimeId) {
        Optional<Showtime> showtime = showtimeRepository.findById(showtimeId);
        return modelMapper.map(showtime.orElse(null), ShowtimeDTO.class);
    }

    @Override
    public ShowtimeDTO updateShowtime(Long id, ShowtimeUpdateDTO showtimeUpdateDTO) {
        Optional<Showtime> showtimeOptional = showtimeRepository.findById(id);
        if (showtimeOptional.isPresent()) {
            Showtime showtime = showtimeOptional.get();
            modelMapper.map(showtimeUpdateDTO, showtime);

            // Check if the hall with the provided hallId exists in the database
            if(showtimeUpdateDTO.getHallId() != null) {
                Hall hall = hallRepository.findById(showtimeUpdateDTO.getHallId())
                        .orElseThrow(() -> new IllegalArgumentException("לא קיים אולם עם המזהה הזה."));

                showtime.setHall(hall);
            }

            // Check if the movie with the provided movieId exists in the database
            if(showtimeUpdateDTO.getMovieId() != null){
                Movie movie = movieRepository.findById(showtimeUpdateDTO.getMovieId())
                        .orElseThrow(() -> new IllegalArgumentException("לא קיים סרט עם המזהה הזה."));
                showtime.setMovie(movie);
            }

            Showtime editedShowtime = showtimeRepository.save(showtime);
            return modelMapper.map(editedShowtime, ShowtimeDTO.class);
        }
        return null;
    }

    @Override
    public boolean deleteShowtime(Long showtimeId) {
        if (showtimeRepository.existsById(showtimeId)) {
            showtimeRepository.deleteById(showtimeId);
            return true;
        }
        return false;
    }
}
