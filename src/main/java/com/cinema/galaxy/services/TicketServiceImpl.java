package com.cinema.galaxy.services;

import com.cinema.galaxy.models.Ticket;
import com.cinema.galaxy.repositories.TicketRepository;
import com.cinema.galaxy.serviceInterfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
