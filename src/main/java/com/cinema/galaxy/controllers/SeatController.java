package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Seat;
import com.cinema.galaxy.services.SeatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/seat")
@RequiredArgsConstructor
public class SeatController {
//    private final SeatServiceImpl seatServiceImpl;

//    @GetMapping
//    public List<Seat> getSeats() {
//        return seatServiceImpl.getSeats();
//    }

}
