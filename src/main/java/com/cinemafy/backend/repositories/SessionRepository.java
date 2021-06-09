package com.cinemafy.backend.repositories;

import com.cinemafy.backend.models.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
