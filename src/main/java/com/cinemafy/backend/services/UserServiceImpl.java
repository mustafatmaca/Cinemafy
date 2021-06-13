package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;
import com.cinemafy.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
