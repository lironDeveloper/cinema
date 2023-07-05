package com.cinema.galaxy.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;

    @Autowired
    public HallService(HallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    public List<Hall> getHalls(){
        return hallRepository.findAll();
    }
}
