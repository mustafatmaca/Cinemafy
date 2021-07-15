package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Salon;

import java.util.List;
import java.util.Set;

public interface SalonService {
    List<Salon> findAll();

    List<Salon> findByCinema(Cinema cinema);

    Set<Salon> findByCinemaFilter(String filter);

    Long count();

    void delete(Salon salon);

    void save(Salon salon);
}
