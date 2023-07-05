package com.cinema.galaxy.showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public ShowtimeService(ShowtimeRepository showtimeRepository){
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> getShowtimes(){
        return showtimeRepository.findAll();
    }
}
