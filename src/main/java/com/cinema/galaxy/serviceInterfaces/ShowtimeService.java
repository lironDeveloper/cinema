package com.cinema.galaxy.serviceInterfaces;

import com.cinema.galaxy.DTOs.Hall.HallCreationDTO;
import com.cinema.galaxy.DTOs.Hall.HallDTO;
import com.cinema.galaxy.DTOs.Hall.HallUpdateDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeCreationDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeUpdateDTO;
import com.cinema.galaxy.models.Showtime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface ShowtimeService {
    public Page<ShowtimeDTO> getShowtimeByMovieIdAndBranchIdAndTimeFilter(
            Long movieId,
            Long branchId,
            Instant fromDate,
            Instant toDate,
            Pageable page
    );

    public Page<ShowtimeDTO> getShowtimeByMovieIdAndBranchId(
            Long movieId,
            Long branchId,
            Pageable page
    );

    public ShowtimeDTO addShowtime(ShowtimeCreationDTO showtimeCreationDTO);
    public ShowtimeDTO getShowtimeById(Long showtimeId);
    ShowtimeDTO updateShowtime(Long id, ShowtimeUpdateDTO showtimeUpdateDTO);
    public boolean deleteShowtime(Long showtimeId);
}
