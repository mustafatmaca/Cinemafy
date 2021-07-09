package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SessionServiceImpl implements SessionService{

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    @Override
    public List<Session> findAll() {
        return StreamSupport.stream(sessionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Session> findByFilm(Film film) {
        return sessionRepository.findByFilm_Id(film.getId());
    }

    @Override
    public List<Session> findBySalon(Salon salon) {
        return sessionRepository.findBySalon_Id(salon.getId());
    }

    @Override
    public Long count() {
        return sessionRepository.count();
    }

    @Override
    public void delete(Session session) {
        sessionRepository.delete(session);
    }

    @Override
    public void save(Session session) {
        sessionRepository.save(session);
    }


}
