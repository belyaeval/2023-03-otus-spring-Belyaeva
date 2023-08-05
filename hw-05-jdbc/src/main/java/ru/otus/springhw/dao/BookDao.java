package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Book;

import java.util.List;

public interface BookDao {
    Book insert(Book book);

    Book getById(long id);

    Book update(Book book, long id);

    Book deleteById(long id);

    List<Book> getAll();

    Book getByName(String name);


}
