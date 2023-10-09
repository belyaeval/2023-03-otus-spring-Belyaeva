package ru.otus.springhw.handler;

import ru.otus.springhw.domain.Genre;

public interface GenreHandler {
    Genre createGenre(Long id, String name);

    Genre createGenre(String name);
}
