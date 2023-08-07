package ru.otus.springhw.service;

import ru.otus.springhw.domain.Book;

import java.util.List;

public interface BookService {
    Book insert(Book book);

    Book getById(long id);

    Book update(Book book, long id);

    Book deleteById(long id);

    List<Book> getAll();

    Book getByName(String name);
}
