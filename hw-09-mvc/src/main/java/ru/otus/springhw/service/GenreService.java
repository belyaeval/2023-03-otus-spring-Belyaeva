package ru.otus.springhw.service;

import ru.otus.springhw.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Long id);

    Genre save(Genre genre);

    Optional<Genre> findByName(String gName);

    Genre updateById(Long id, String name);
}
