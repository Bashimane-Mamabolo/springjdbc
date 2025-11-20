package com.bash.springjdbc.dao;

import com.bash.springjdbc.dao.impl.BookDaoImpl;
import com.bash.springjdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

// unit tests
@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookUnderTest;

    @Test
    public void testThatCreateBookGenerateCorrectSql() {
        // Arrange (Dependencies and method parameters)
        Book book = Book.builder()
                .isbn("ISBN")
                .title("Title")
                .authorId(1L)
                .build();
        // Act (call the method we're testing)
        bookUnderTest.create(book);
        // Assert and verify our expected result
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("ISBN"),
                eq("Title"),
                eq(1L)
        );
    }
}
