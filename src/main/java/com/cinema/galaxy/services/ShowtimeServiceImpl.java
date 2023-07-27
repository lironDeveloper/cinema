package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.repositories.ShowtimeRepository;
import com.cinema.galaxy.serviceInterfaces.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> getShowtimes(){
        return showtimeRepository.findAll();
    }
}
