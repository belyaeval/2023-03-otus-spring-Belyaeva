package ru.otus.springhw.service;

import ru.otus.springhw.domain.Author;

public interface AuthorService {
    Author findById(String id);

    Author save(Author author);

    Author findByName(String name);
}
