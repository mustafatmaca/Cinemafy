package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;

import javax.annotation.PostConstruct;
import java.util.List;

public interface CinemaService {
    List<Cinema> findAll();

    Long count();

    void delete(Cinema cinema);

    void save(Cinema cinema);

    @PostConstruct
    void populateTestData();
}
