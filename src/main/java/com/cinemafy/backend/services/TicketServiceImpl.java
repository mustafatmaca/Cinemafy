package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.models.User;
import com.cinemafy.backend.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findByUserId(User user) {
        return ticketRepository.findTicketByUser(user);
    }

    @Override
    public List<Ticket> findBySession(Session session) {
        return ticketRepository.findBySession_Id(session.getId());
    }

    @Override
    public Long count() {
        return ticketRepository.count();
    }

    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
