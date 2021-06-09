package com.cinemafy.backend.repositories;

import com.cinemafy.backend.models.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
}
