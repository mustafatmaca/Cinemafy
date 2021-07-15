package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Film;

import java.util.List;
import java.util.Set;

public interface FilmService {
    List<Film> findAll();

    List<Film> findByCategory(Category category);

    Film findByName(String name);

    Set<Film> findByNameFilter(String filter);

    Set<Film> findByCategoryFilter(String filter);

    Long count();

    void delete(Film film);

    void save(Film film);
}
