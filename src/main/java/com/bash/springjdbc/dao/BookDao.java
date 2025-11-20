package com.bash.springjdbc.dao;

import com.bash.springjdbc.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);
}
