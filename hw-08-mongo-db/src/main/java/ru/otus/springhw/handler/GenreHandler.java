package ru.otus.springhw.handler;

import ru.otus.springhw.domain.Genre;

public interface GenreHandler {
    Genre createGenre(String id, String name);

    Genre createGenre(String name);
}
