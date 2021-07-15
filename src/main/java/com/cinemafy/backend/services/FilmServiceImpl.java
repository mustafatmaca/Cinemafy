package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public Film findByName(String name) {
        List<Film> result =  filmRepository.findByName(name);
        if (result.size()==0){
            return new Film();
        }

        return result.get(0);
    }

    @Override
    public Set<Film> findByNameFilter(String filter) {
        Set<Film> filmSet = new HashSet<>();
        filmRepository.findByNameContainingIgnoreCase(filter).iterator().forEachRemaining(filmSet::add);
        return filmSet;
    }

    @Override
    public Set<Film> findByCategoryFilter(String filter) {
        Set<Film> filmSet = new HashSet<>();
        filmRepository.findByCategory_GenreContainingIgnoreCase(filter).iterator().forEachRemaining(filmSet::add);
        return filmSet;
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
