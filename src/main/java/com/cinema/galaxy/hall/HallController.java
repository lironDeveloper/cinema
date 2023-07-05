package com.cinema.galaxy.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/halls")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService){
        this.hallService = hallService;
    }

    @GetMapping
    public List<Hall> getHalls() {
        return hallService.getHalls();
    }

}
