package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.domain.Genre;
import ru.otus.springhw.handler.GenreHandler;
import ru.otus.springhw.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreDerviceCommands {
    private final GenreService genreService;

    private final GenreHandler genreHandler;

    @ShellMethod(value = "Get genre", key = {"gg", "get-g"})
    public String getGenre(long id) {
        Genre genre = genreService.getById(id);

        return genre == null ? String.format("Genre with id %d doesn't exist", id) :
                String.format("You got genre № %d: %s", id, genre.getName());
    }

    @ShellMethod(value = "Add genre", key = {"ag", "create-g"})
    public String addGenre(String gName) {
        Genre genre = genreHandler.createGenre(gName);

        genre = genreService.insert(genre);

        return genre == null ? String.format("Genre '%s' is already exists", gName) :
                String.format("You inserted genre № %d: %s", genre.getId(), genre.getName());
    }
}
