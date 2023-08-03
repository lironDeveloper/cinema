package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Showtime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    Page<Showtime> findByMovieIdAndHall_BranchIdAndStartTimeBetween(Long movie_id, Long hall_branch_id, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
}
