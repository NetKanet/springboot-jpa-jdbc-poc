package com.example.jpajdbc.repository;

import com.example.jpajdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
