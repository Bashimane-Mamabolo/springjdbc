package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.TestDataUtil;
import com.bash.springjdbc.dao.AuthorDao;
import com.bash.springjdbc.domain.Author;
import com.bash.springjdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    public void testThatMultipleBooksCanBeCreatedAndReturned() {
        // Arrange
        // Each book will have its own author
        // Authors
        Author author1 = TestDataUtil.createTestAuthor();
        Author author2 = TestDataUtil.createTestAuthorA();
        Author author3 = TestDataUtil.createTestAuthorB();
        // Books
        Book book1 = TestDataUtil.createTestBook();
        Book book2 = TestDataUtil.createTestBookA();
        Book book3 = TestDataUtil.createTestBookB();

        // Act
        // Authors
        authorDao.create(author1);
        authorDao.create(author2);
        authorDao.create(author3);
        // Books
        underTest.create(book1);
        underTest.create(book2);
        underTest.create(book3);
        List<Book> books = underTest.find();

        // Assert
        assertThat(books).hasSize(3);
        assertThat(books).contains(book1, book2, book3);
    }

    @Test
    public void testThatBookCanBeUpdatedAndReturned() {
        // Arrange
        // Authors
        Author author1 = TestDataUtil.createTestAuthor();
        // Books
        Book book1 = TestDataUtil.createTestBook();

        // Act
        // Authors
        authorDao.create(author1);
        // Books
        book1.setAuthorId(author1.getId());
        underTest.create(book1);
        book1.setTitle("Updated Title");
        underTest.update(book1.getIsbn(), book1);
        Optional<Book> optionalBook = underTest.findOne(book1.getIsbn());

        // Assert
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(book1);
    }
}
