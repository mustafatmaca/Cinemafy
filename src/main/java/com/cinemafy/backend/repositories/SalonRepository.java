package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Salon;
import org.springframework.data.repository.CrudRepository;

public interface SalonRepository extends CrudRepository<Salon, Long> {
}
