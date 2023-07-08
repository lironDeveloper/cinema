package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/showtime")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService){
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<Showtime> getShowtimes() {
        return showtimeService.getShowtimes();
    }

}
