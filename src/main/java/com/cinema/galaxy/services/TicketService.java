package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Ticket;
import com.cinema.galaxy.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
