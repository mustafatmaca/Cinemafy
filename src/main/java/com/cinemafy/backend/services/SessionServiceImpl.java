package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.repositories.SessionRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
