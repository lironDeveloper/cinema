package com.cinema.galaxy.controllers;

import com.cinema.galaxy.services.HallServiceImpl;
import com.cinema.galaxy.models.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/hall")
public class HallController {
    @Autowired
    private HallServiceImpl hallServiceImpl;

    @GetMapping
    public List<Hall> getHalls() {
        return hallServiceImpl.getHalls();
    }

}
