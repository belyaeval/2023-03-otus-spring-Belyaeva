package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long id);

    Author save(Author author);

    Author findByName(String name);
}
