package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
}
