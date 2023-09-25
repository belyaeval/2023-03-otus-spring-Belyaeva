package ru.otus.springhw.service;

import ru.otus.springhw.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(String id);

    Author save(Author author);

    Optional<Author> findByName(String name);

    List<Author> findAll();

    Author updateById(String id, String name);
}
