package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.services.ShowtimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/showtime")
public class ShowtimeController {
    @Autowired
    private ShowtimeServiceImpl showtimeServiceImpl;

    @GetMapping
    public List<Showtime> getShowtimes() {
        return showtimeServiceImpl.getShowtimes();
    }

}
