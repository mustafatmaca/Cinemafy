package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Salon;

import java.util.List;

public interface SalonService {
    List<Salon> findAll();

    List<Salon> findByCinema(Cinema cinema);

    Long count();

    void delete(Salon salon);

    void save(Salon salon);
}
