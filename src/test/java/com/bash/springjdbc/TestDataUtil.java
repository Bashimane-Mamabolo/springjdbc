package com.bash.springjdbc;

import com.bash.springjdbc.domain.Author;
import com.bash.springjdbc.domain.Book;

public final class TestDataUtil {

    private TestDataUtil() {}

    // Authors
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Bash")
                .age(31)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(2L)
                .name("Simon")
                .age(31)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(3L)
                .name("ByteBash")
                .age(31)
                .build();
    }


    // Books
    public static Book createTestBook() {
        return Book.builder()
                .isbn("ISBN")
                .title("Title")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("ISBN1")
                .title("Title")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("ISBN2")
                .title("Title")
                .authorId(3L)
                .build();
    }
}
