package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Session;

import java.util.List;

public interface SessionService {
    List<Session> findAll();

    Long count();

    void delete(Session session);

    void save(Session session);
}
