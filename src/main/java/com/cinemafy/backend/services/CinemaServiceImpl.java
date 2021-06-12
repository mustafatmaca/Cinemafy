package com.cinemafy.backend.services;

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.repositories.CinemaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    @Override
    @PostConstruct
    public void populateTestData() {
            if (cinemaRepository.count() == 0) {
                Random r = new Random(0);
                cinemaRepository.saveAll(
                        Stream.of("Edirne-Erasta AVM", "İstanbul-Forum İstanbul", "Kayseri-Forum Kayseri",
                                "İzmir-Erasta İzmir AVM")
                                .map(name -> {
                                    String[] split = name.split("-");
                                    Cinema cinema = new Cinema();
                                    cinema.setCity(split[0]);
                                    cinema.setName(split[1]);
                                    cinema.setUsers(null);
                                    return cinema;
                                }).collect(Collectors.toList()));
            }

    }


}
