package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.bash.springjdbc.TestDataUtil.createTestAuthor;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

// Unit tests
@ExtendWith(MockitoExtension.class) // Set it for testing with mockito
public class AuthorDaoImplTests {

    // @Mock and @InjectMocks are a short to setting this in a setup function
    // For every test, the class under-test is created and its dependencies/collaborators injected.

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        // Arrange
        Author author = createTestAuthor();
        // Act
        underTest.create(author);
        // Asset
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L),
                eq("Bash"),
                eq(31)
        );

    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        // Arrange
        // Act
        underTest.findOne(1l);
        // Assert or Verify
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindAllGeneratesCorrectSql() {
        // Arrange
        // Act
        underTest.find();
        // Assert or verify
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        // Arrange
        Author author = createTestAuthor();

        // Act
        underTest.update(author.getId(), author);
        //Assert or verify
        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(1L),
                eq("Bash"),
                eq(31),
                eq(1L)
        );


    }


}
