package com.cinema.galaxy.controllers;

import com.cinema.galaxy.models.Ticket;
import com.cinema.galaxy.services.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketServiceImpl ticketServiceImpl;

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketServiceImpl.getTickets();
    }

}
