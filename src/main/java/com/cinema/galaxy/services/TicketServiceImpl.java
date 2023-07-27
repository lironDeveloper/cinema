package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Ticket;
import com.cinema.galaxy.repositories.TicketRepository;
import com.cinema.galaxy.serviceInterfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
