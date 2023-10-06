package com.cinema.galaxy.repositories;

import com.cinema.galaxy.models.Showtime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    Page<Showtime> findByMovieIdAndHall_BranchIdAndStartTimeBetween(Long movie_id, Long hall_branch_id, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM Showtime s " +
            "WHERE s.hall.id = :hallId " +
            "AND (:showtimeId IS NULL OR s.id != :showtimeId) " +
            "AND ((s.startTime BETWEEN :startTime AND :endTime) " +
            "OR (s.endTime BETWEEN :startTime AND :endTime) " +
            "OR (s.startTime > :startTime AND s.endTime < :endTime)" +
            "OR (s.startTime < :startTime AND s.endTime > :endTime))")
    boolean existsConflictingShowtime(
            @Param("showtimeId") Long showtimeId,
            @Param("hallId") Long hallId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    Page<Showtime> findByMovieIdAndHall_BranchId(Long movie_id, Long hall_branch_id, Pageable pageable);
}
