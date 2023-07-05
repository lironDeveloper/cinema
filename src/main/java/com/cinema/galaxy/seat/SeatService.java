package com.cinema.galaxy.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeats(){
        return seatRepository.findAll();
    }
}
