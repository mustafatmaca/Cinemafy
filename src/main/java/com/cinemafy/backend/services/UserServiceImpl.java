package com.cinemafy.backend.services;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;
import com.cinemafy.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public User login(String email, String password, String loginType) {
        List<User> result =  userRepository.findByEmailAndPassword(email,password);
        if (result.size()==0){
            return new User();
        }
        else if (result.get(0).getUserType().equals(loginType)){
            return result.get(0);
        }
        else {
            return new User();
        }
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
