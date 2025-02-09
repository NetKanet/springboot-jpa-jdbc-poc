package com.example.jpajdbc.controller;

import com.example.jpajdbc.entity.User;
import com.example.jpajdbc.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/jpa")
    public List<User> getUsersWithJpa() {
        return userService.getAllUsersWithJpa();
    }

    @PostMapping("/jpa")
    public void addUserWithJpa(@RequestBody User user) {
        userService.addUserWithJpa(user);
    }

    @GetMapping("/jdbc")
    public List<User> getUsersWithJdbc() {
        return userService.getAllUsersWithJdbc();
    }

    @PostMapping("/jdbc")
    public void addUserWithJdbc(@RequestBody User user) {
        userService.addUserWithJdbc(user);
    }
}
