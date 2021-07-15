package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;

import java.util.List;
import java.util.Set;

public interface CinemaService {
    List<Cinema> findAll();

    Set<Cinema> findByNameFilter(String filter);

    Set<Cinema> findByCityFilter(String filter);

    Long count();

    void delete(Cinema cinema);

    void save(Cinema cinema);
}
