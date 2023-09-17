package ru.otus.springhw.service;

import ru.otus.springhw.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> findById(String id);

    Book updateById(String id, Book book);

    void deleteById(String id);

    List<Book> findAll();

    Optional<Book> findByName(String name);
}
