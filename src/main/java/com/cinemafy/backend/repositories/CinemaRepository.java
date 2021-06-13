package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
}
