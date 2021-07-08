package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
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
    public List<Film> findByCategory(Category category) {
        return filmRepository.findByCategory_Id(category.getId());
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
