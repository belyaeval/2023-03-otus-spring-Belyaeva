package ru.otus.springhw.handler;

import ru.otus.springhw.domain.Genre;

public interface GenreHandler {
    Genre createGenre(long id, String name);

    Genre createGenre(String name);
}
