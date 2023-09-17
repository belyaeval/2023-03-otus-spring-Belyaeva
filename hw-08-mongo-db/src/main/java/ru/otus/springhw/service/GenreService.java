package ru.otus.springhw.service;

import ru.otus.springhw.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(String id);

    Genre save(Genre genre);

    Optional<Genre> findByName(String gName);

    Genre updateById(String id, String name);
}
