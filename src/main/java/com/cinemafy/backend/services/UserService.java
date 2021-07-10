package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findUser(Long id);

    Long count();

    User login(String email, String password, String loginType);

    void delete(User user);

    void save(User user);
}
