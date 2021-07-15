package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long>, JpaRepository<Film, Long> {
    List<Film> findByCategory_Id(Long id);
    List<Film> findByName(String name);
    List<Film> findByNameContainingIgnoreCase(String filter);
    List<Film> findByCategory_GenreContainingIgnoreCase(String filter);
}
