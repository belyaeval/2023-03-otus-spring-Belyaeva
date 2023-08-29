package ru.otus.springhw.service;

import ru.otus.springhw.domain.Genre;

public interface GenreService {
    Genre findById(long id);

    Genre save(Genre genre);

    Genre findByName(String name);
}
