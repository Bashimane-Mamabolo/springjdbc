package com.bash.springjdbc;

import com.bash.springjdbc.domain.Author;
import com.bash.springjdbc.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {
    }
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Bash")
                .age(31)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("ISBN")
                .title("Title")
                .authorId(1L)
                .build();
    }
}
