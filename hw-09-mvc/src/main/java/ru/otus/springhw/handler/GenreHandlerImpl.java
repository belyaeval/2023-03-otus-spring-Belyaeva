package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Genre;

@Component
public class GenreHandlerImpl implements GenreHandler {
    @Override
    public Genre createGenre(Long id, String name) {
        return new Genre(id, name);
    }

    @Override
    public Genre createGenre(String name) {
        return new Genre(name);
    }
}
