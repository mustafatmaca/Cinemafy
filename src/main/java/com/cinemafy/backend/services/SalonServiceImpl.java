package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.repositories.SalonRepository;

import java.util.HashSet;
import java.util.Set;

public class SalonServiceImpl implements SalonService{

    private final SalonRepository salonRepository;

    public SalonServiceImpl(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    @Override
    public Set<Salon> getSalons() {
        Set<Salon> salonSet = new HashSet<>();
        salonRepository.findAll().iterator().forEachRemaining(salonSet::add);
        return salonSet;
    }
}
