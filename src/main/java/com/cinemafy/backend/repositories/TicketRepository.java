package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long>, JpaRepository<Ticket, Long> {
    List<Ticket> findTicketByUser(User user);
    List<Ticket> findBySession_Id(Long id);
}
