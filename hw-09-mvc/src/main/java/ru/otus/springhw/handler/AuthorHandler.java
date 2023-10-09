package ru.otus.springhw.handler;

import ru.otus.springhw.domain.Author;

public interface AuthorHandler {
    Author createAuthor(Long id, String name);

    Author createAuthor(String name);

    String convertToString(Author author);
}
