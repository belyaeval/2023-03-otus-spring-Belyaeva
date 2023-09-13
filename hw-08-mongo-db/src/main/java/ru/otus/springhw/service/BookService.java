package ru.otus.springhw.service;

import ru.otus.springhw.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);

    Book findById(String id);

    Book updateById(String id, Book book);

    Book deleteById(String id);

    List<Book> findAll();

    Book findByName(String name);
}
