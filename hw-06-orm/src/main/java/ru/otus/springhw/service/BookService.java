package ru.otus.springhw.service;

import ru.otus.springhw.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);

    Book findById(long id);

    Book update(Book book, long id);

    Book deleteById(long id);

    List<Book> findAll();

    Book findByName(String name);

    void addComment(long bookId, String commText);

    List<String> getAllBookComments(long bookId);
}
