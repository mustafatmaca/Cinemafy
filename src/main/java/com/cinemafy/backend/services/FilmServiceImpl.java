package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.repositories.FilmRepository;

import java.util.HashSet;
import java.util.Set;

public class FilmServiceImpl implements FilmService{

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Set<Film> getFilms() {
        Set<Film> filmSet = new HashSet<>();
        filmRepository.findAll().iterator().forEachRemaining(filmSet::add);
        return filmSet;
    }
}
