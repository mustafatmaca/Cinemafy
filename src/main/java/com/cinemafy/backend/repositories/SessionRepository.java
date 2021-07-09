package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long>, JpaRepository<Session, Long> {
    List<Session> findByFilm_Id(Long id);
    List<Session> findBySalon_Id(Long id);
}
