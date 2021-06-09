package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.repositories.CinemaRepository;

import java.util.HashSet;
import java.util.Set;

public class CinemaServiceImpl implements CinemaService{
    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }


    @Override
    public Set<Cinema> getCinemas() {
        Set<Cinema> cinemaSet  = new HashSet<>();
        cinemaRepository.findAll().iterator().forEachRemaining(cinemaSet::add);
        return cinemaSet;
    }

}
