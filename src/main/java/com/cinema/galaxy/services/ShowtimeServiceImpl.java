package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Showtime;
import com.cinema.galaxy.repositories.ShowtimeRepository;
import com.cinema.galaxy.serviceInterfaces.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> getShowtimes(){
        return showtimeRepository.findAll();
    }
}
