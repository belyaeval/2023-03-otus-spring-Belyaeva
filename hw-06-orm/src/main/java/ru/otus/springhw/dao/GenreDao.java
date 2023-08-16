package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findById(long id);

    Genre save(Genre genre);

    Genre findByName(String name);
}
