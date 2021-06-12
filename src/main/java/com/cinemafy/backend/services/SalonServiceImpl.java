package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.repositories.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SalonServiceImpl implements SalonService{

    private final SalonRepository salonRepository;

    public SalonServiceImpl(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }


    @Override
    public List<Salon> findAll() {
        return StreamSupport.stream(salonRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return salonRepository.count();
    }

    @Override
    public void delete(Salon salon) {
        salonRepository.delete(salon);
    }

    @Override
    public void save(Salon salon) {
        salonRepository.save(salon);
    }
}
