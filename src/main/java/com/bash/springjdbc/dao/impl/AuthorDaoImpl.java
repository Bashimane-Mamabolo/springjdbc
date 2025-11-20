package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.dao.AuthorDao;
import com.bash.springjdbc.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;


public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create an author in our database
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(), author.getName(), author.getAge()
        );
    }
}
