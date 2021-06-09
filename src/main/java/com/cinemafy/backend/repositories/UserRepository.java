package com.cinemafy.backend.repositories;

import com.cinemafy.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
