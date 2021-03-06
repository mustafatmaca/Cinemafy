package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.models.User;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    List<Ticket> findByUserId(User user);

    List<Ticket> findBySession(Session session);

    Long count();

    void delete(Ticket ticket);

    void save(Ticket ticket);
}
