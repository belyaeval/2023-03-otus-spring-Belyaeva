package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Genre;

@Component
public class GenreHandler {
    public Genre createGenre(long id, String name) {
        return new Genre(id, name);
    }

    public Genre createGenre(String name) {
        return new Genre(name);
    }
}
