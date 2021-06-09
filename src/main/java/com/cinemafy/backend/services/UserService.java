package com.cinemafy.backend.services;

import com.cinemafy.backend.models.User;

import java.util.Set;

public interface UserService {
    Set<User> getUsers();
}
