package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    Long count();

    void delete(Film film);

    void save(Film film);
}
