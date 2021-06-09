package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Cinema;

import java.util.Set;

public interface CinemaService {
    Set<Cinema> getCinemas();
}
