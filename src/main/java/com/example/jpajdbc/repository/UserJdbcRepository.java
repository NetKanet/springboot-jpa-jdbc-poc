package com.example.jpajdbc.repository;

import com.example.jpajdbc.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user", (rs, rowNum) -> new User(rs.getString("name")));
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user (name) VALUES (?)", user.getName());
    }
}
