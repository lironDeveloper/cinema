package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Showtime.ShowtimeCreationDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeDTO;
import com.cinema.galaxy.DTOs.Showtime.ShowtimeUpdateDTO;
import com.cinema.galaxy.exceptions.UniqueException;
import com.cinema.galaxy.services.ShowtimeServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/api/showtime")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class ShowtimeController {
    private final ShowtimeServiceImpl showtimeServiceImpl;

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ShowtimeDTO> addShowtime(@Valid @RequestBody ShowtimeCreationDTO showtimeCreationDTO){
        ShowtimeDTO addedShowtime = showtimeServiceImpl.addShowtime(showtimeCreationDTO);
        return new ResponseEntity<>(addedShowtime, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowtimeDTO> getShowtimeById(@PathVariable("id") Long id) {
        ShowtimeDTO showtime = showtimeServiceImpl.getShowtimeById(id);
        if(showtime != null) {
            return ResponseEntity.ok(showtime);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        if (showtimeServiceImpl.deleteShowtime(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ShowtimeDTO> updateShowtime(@PathVariable Long id, @Valid @RequestBody ShowtimeUpdateDTO showtimeUpdateDTO) {
        try {
            ShowtimeDTO showtime = showtimeServiceImpl.updateShowtime(id, showtimeUpdateDTO);
            if (showtime != null) {
                return new ResponseEntity<>(showtime, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (org.springframework.dao.DataIntegrityViolationException e){ // Update transaction we check here and not in the service layer since springs functionality
            throw new UniqueException("הקרנה של סרט זה באולם זה בשעה הזו כבר קיימת.");
        }
    }

    @GetMapping("/movie/{movieId}/branch/{branchId}/time")
    public ResponseEntity<List<ShowtimeDTO>> getShowtimeByMovieIdAndBranchIdAndTimeFilter(
            @PathVariable("movieId") Long movieId,
            @PathVariable("branchId") Long branchId,
            @NotNull(message = "יש לציין תאריך תחילת טווח") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)Instant fromDate,
            @NotNull(message = "יש לציין תאריך סוף טווח")  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)Instant toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ShowtimeDTO> showtimeList = showtimeServiceImpl.getShowtimeByMovieIdAndBranchIdAndTimeFilter(
                movieId,
                branchId,
                fromDate,
                toDate,
                pageable
        );
        return ResponseEntity.ok(showtimeList.getContent());
    }

    @GetMapping("/movie/{movieId}/branch/{branchId}")
    public ResponseEntity<List<ShowtimeDTO>> getShowtimeByMovieIdAndBranchId(
            @PathVariable("movieId") Long movieId,
            @PathVariable("branchId") Long branchId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ShowtimeDTO> showtimeList = showtimeServiceImpl.getShowtimeByMovieIdAndBranchId(
                movieId,
                branchId,
                pageable
        );
        return ResponseEntity.ok(showtimeList.getContent());
    }
}
