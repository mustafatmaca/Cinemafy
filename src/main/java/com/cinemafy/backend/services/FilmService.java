package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    List<Film> findByCategory(Category category);

    Long count();

    void delete(Film film);

    void save(Film film);
}
