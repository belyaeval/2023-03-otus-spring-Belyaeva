package ru.otus.springhw.service;

import ru.otus.springhw.domain.Genre;

public interface GenreService {
    Genre getById(long id);

    Genre insert(Genre genre);

    Genre getByName(String name);
}
