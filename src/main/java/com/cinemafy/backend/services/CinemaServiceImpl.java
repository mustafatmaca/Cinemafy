package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.repositories.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CinemaServiceImpl implements CinemaService{
    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }


    @Override
    public List<Cinema> findAll() {
        return StreamSupport.stream(cinemaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Cinema> findByNameFilter(String filter) {
        Set<Cinema> cinemaSet = new HashSet<>();
        cinemaRepository.findByNameContainingIgnoreCase(filter).iterator().forEachRemaining(cinemaSet::add);
        return cinemaSet;
    }

    @Override
    public Set<Cinema> findByCityFilter(String filter) {
        Set<Cinema> cinemaSet = new HashSet<>();
        cinemaRepository.findByCityContainingIgnoreCase(filter).iterator().forEachRemaining(cinemaSet::add);
        return cinemaSet;
    }

    @Override
    public Long count() {
        return cinemaRepository.count();
    }

    @Override
    public void delete(Cinema cinema) {
        cinemaRepository.delete(cinema);
    }

    @Override
    public void save(Cinema cinema) {
        cinemaRepository.save(cinema);
    }
}
