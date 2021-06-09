package com.cinemafy.backend.services;

import com.cinemafy.backend.models.User;
import com.cinemafy.backend.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> getUsers() {
        Set<User> userSet = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(userSet::add);
        return userSet;
    }
}
