package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema, Long>, JpaRepository<Cinema, Long> {
    List<Cinema> findByNameContainingIgnoreCase(String filter);
    List<Cinema> findByCityContainingIgnoreCase(String filter);
}
