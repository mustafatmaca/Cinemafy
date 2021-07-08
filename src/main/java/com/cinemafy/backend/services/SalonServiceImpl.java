package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
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
    public List<Salon> findByCinema(Cinema cinema) {
        return salonRepository.findByCinema_Id(cinema.getId());
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
