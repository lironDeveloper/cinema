package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Ticket;
import com.cinema.galaxy.services.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketServiceImpl.getTickets();
    }

}
