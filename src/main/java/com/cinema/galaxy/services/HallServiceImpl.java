package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Hall;
import com.cinema.galaxy.repositories.HallRepository;
import com.cinema.galaxy.serviceInterfaces.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    @Override
    public List<Hall> getHalls(){
        return hallRepository.findAll();
    }
}
