package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.repositories.SessionRepository;

import java.util.HashSet;
import java.util.Set;

public class SessionServiceImpl implements SessionService{

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Set<Session> getSessions() {
        Set<Session> sessionSet = new HashSet<>();
        sessionRepository.findAll().iterator().forEachRemaining(sessionSet::add);
        return sessionSet;
    }
}
