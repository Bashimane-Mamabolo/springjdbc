package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.bash.springjdbc.TestDataUtil.createTestBook;
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
        Book book = createTestBook();
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

    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        // Arrange
        // Act
        bookUnderTest.findOne("ISBN");
        // Assert or verify
        verify(jdbcTemplate).query(
              eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
              ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
              eq("ISBN")
        );

    }

    @Test
    public void testThatFindAllGeneratesCorrectSql() {
        // Arrange
        // Act
        bookUnderTest.find();
        // Assert or verify
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        // Arrange
        Book book = createTestBook();

        // Act
        bookUnderTest.update(book.getIsbn(), book);

        // Assert
        verify(jdbcTemplate).update(
                eq("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"),
                eq("ISBN"),
                eq(book.getTitle()),
                eq(1L),
                eq("ISBN")
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        // Arrange
        Book book = createTestBook();
        // Act
        bookUnderTest.delete(book.getIsbn());
        // Assert or verify
        verify(jdbcTemplate).update(
                eq("DELETE FROM books WHERE isbn = ?"),
                eq("ISBN")
        );
    }
}
