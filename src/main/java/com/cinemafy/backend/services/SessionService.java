package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.models.Session;

import java.util.List;
import java.util.Set;

public interface SessionService {
    List<Session> findAll();

    List<Session> findByFilm(Film film);

    List<Session> findBySalon(Salon salon);

    List<Session> findBySalonAndFilm(Salon salon, Film film);

    Set<Session> findByFilmFilter(String filter);

    Set<Session> findByCinemaFilter(String filter);

    Long count();

    void delete(Session session);

    void save(Session session);
}
