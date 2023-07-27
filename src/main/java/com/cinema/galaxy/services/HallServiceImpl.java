package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Hall;
import com.cinema.galaxy.repositories.HallRepository;
import com.cinema.galaxy.serviceInterfaces.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<Hall> getHalls(){
        return hallRepository.findAll();
    }
}
