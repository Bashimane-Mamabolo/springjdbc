package com.bash.springjdbc.dao.impl;

import com.bash.springjdbc.TestDataUtil;
import com.bash.springjdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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

}
