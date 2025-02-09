package com.example.jpajdbc.service;

import com.example.jpajdbc.entity.User;
import com.example.jpajdbc.repository.UserJdbcRepository;
import com.example.jpajdbc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserJdbcRepository userJdbcRepository;

    public UserService(UserRepository userRepository, UserJdbcRepository userJdbcRepository) {
        this.userRepository = userRepository;
        this.userJdbcRepository = userJdbcRepository;
    }

    // JPA usage
    public List<User> getAllUsersWithJpa() {
        return userRepository.findAll();
    }

    public void addUserWithJpa(User user) {
        userRepository.save(user);
    }

    // JDBC usage
    public List<User> getAllUsersWithJdbc() {
        return userJdbcRepository.findAll();
    }

    public void addUserWithJdbc(User user) {
        userJdbcRepository.save(user);
    }
}
