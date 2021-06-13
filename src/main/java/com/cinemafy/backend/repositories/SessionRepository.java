package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
