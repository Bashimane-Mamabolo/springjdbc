package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.TestDataUtil;
import com.bash.springjdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndReturned() {
        // Arrange
        Author author = TestDataUtil.createTestAuthor();
        // Act
        underTest.create(author);
        Optional<Author> optionalAuthor = underTest.findOne(author.getId());
        // Assert or verify
        assertThat(optionalAuthor).isPresent();
        assertThat(optionalAuthor.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndReturned() {

        // Arrange
        Author author1 = TestDataUtil.createTestAuthor();
        Author author2 = TestDataUtil.createTestAuthorA();
        Author author3 = TestDataUtil.createTestAuthorB();

        // Act
        // create authors
        underTest.create(author1);
        underTest.create(author2);
        underTest.create(author3);
        // find all authors from db
        List<Author> authors = underTest.find();

        // Assert or verify
        assertThat(authors).hasSize(3);
        assertThat(authors).contains(author1, author2, author3);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        // Arrange
        Author author1 = TestDataUtil.createTestAuthor();

        // Act
        underTest.create(author1);
        author1.setName(author1.getName() + " updated");
        underTest.update(author1.getId(), author1);
        Optional<Author> results = underTest.findOne(author1.getId());

        // Assert
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(author1);



    }

}
