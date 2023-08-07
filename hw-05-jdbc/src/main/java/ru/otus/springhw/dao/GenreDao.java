package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Genre;

public interface GenreDao {
    Genre getById(long id);

    Genre insert(Genre genre);

    Genre getByName(String name);
}
