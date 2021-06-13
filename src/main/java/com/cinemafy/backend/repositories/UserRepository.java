package com.cinemafy.backend.repositories;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
