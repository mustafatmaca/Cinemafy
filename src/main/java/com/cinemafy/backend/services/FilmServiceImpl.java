package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.repositories.FilmRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FilmServiceImpl implements FilmService{

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @Override
    public List<Film> findAll() {
        return StreamSupport.stream(filmRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return filmRepository.count();
    }

    @Override
    public void delete(Film film) {
        filmRepository.delete(film);
    }

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }
}
