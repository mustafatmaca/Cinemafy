package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Salon;

import java.util.List;

public interface SalonService {
    List<Salon> findAll();

    Long count();

    void delete(Salon salon);

    void save(Salon salon);
}
