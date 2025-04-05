package ca.sheridancollege.suranim.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThreadRepository {
    @Autowired private JdbcTemplate jdbc;

    public List<Thread> findAll() {
        return jdbc.query("SELECT * FROM threads ORDER BY created_at DESC",
            new BeanPropertyRowMapper<>(Thread.class));
    }

    public Thread findById(int id) {
        return jdbc.queryForObject("SELECT * FROM threads WHERE id = ?",
            new BeanPropertyRowMapper<>(Thread.class), id);
    }
}