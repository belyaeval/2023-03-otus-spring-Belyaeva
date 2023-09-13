package ru.otus.springhw.service;

import ru.otus.springhw.domain.Genre;

public interface GenreService {
    Genre findById(String id);

    Genre save(Genre genre);

    Genre findByName(String name);
}
