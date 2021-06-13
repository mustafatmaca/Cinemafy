package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    Long count();

    void delete(User user);

    void save(User user);
}
