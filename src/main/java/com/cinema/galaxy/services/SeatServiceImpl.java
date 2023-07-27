package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Seat;
import com.cinema.galaxy.repositories.SeatRepository;
import com.cinema.galaxy.serviceInterfaces.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getSeats(){
        return seatRepository.findAll();
    }
}
