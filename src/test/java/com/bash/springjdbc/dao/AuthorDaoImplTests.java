package com.bash.springjdbc.dao;

import com.bash.springjdbc.dao.impl.AuthorDaoImpl;
import com.bash.springjdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

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
        Author author = Author.builder()
                .id(1L)
                .name("Bash")
                .age(31)
                .build();
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

}
