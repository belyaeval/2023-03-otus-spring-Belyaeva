package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    Optional<Book> findById(long id);

    Book update(Book book);

    void delete(Book book);

    List<Book> findAll();

    Book findByName(String name);
}
