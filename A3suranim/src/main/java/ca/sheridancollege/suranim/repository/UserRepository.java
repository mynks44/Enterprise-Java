package ca.sheridancollege.suranim.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.suranim.model.User;

@Repository
public class UserRepository {
    @Autowired private JdbcTemplate jdbc;

    public User findByUsername(String username) {
        return jdbc.queryForObject(
            "SELECT * FROM users WHERE username = ?",
            new BeanPropertyRowMapper<>(User.class),
            username
        );
    }

    public void save(User user) {
        jdbc.update("INSERT INTO users (username, password, role) VALUES (?, ?, ?)",
            user.getUsername(), user.getPassword(), user.getRole());
    }
}
