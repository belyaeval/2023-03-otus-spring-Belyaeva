package ru.otus.springhw.service;

import ru.otus.springhw.domain.Author;

public interface AuthorService {
    Author getById(long id);

    Author insert(Author author);

    Author getByName(String name);
}
