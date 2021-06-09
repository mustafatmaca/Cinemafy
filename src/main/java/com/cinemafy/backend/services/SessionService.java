package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Session;

import java.util.Set;

public interface SessionService {
    Set<Session> getSessions();
}
