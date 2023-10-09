package ru.otus.springhw.handler;

import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

public interface BookHandler {
    Book createBook(Long id, String bookName, Author author, Genre genre);

    Book createBook(String bookName, Author author, Genre genre);

    String convertToString(Book book);
}
