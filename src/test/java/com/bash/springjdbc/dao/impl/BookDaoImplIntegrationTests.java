package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.TestDataUtil;
import com.bash.springjdbc.dao.AuthorDao;
import com.bash.springjdbc.domain.Author;
import com.bash.springjdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {

    private BookDaoImpl underTest;
    private AuthorDao authorDao;
    @Autowired
    public BookDaoImplIntegrationTests(
            BookDaoImpl underTest,
            AuthorDaoImpl authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }
    @Test
    public void testThatBookCanBeCreatedAndReturned() {
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook();
        // Act
        authorDao.create(author);
        underTest.create(book);
        Optional<Book> optionalBook = underTest.findOne(book.getIsbn());
        // Assert
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(book);
    }
}
