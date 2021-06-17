package com.cinemafy.backend.repositories;

import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findTicketByUser(User user);
}
