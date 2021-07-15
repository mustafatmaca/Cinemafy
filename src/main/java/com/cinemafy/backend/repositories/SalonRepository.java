package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalonRepository extends CrudRepository<Salon, Long>, JpaRepository<Salon, Long> {
    List<Salon> findByCinema_Id(Long id);
    List<Salon> findByCinema_NameContainingIgnoreCase(String filter);
}
