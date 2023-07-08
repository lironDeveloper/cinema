package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Seat;
import com.cinema.galaxy.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/seat")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping
    public List<Seat> getSeats() {
        return seatService.getSeats();
    }

}
