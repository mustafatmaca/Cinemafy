package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Film;

import java.util.Set;

public interface FilmService {
    Set<Film> getFilms();
}
