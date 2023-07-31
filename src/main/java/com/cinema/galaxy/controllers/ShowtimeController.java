package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.services.ShowtimeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/showtime")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeServiceImpl showtimeServiceImpl;

    @GetMapping
    public List<Showtime> getShowtimes() {
        return showtimeServiceImpl.getShowtimes();
    }

}
