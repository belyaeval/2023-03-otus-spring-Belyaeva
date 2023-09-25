package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.domain.Genre;
import ru.otus.springhw.handler.GenreHandler;
import ru.otus.springhw.service.GenreService;

import java.util.NoSuchElementException;

@ShellComponent
@RequiredArgsConstructor
public class GenreServiceCommands {
    private final GenreService genreService;

    private final GenreHandler genreHandler;

    @ShellMethod(value = "Get genre", key = {"gg", "get-g"})
    public String getGenre(String id) {
        try {
            Genre genre = genreService.findById(id).orElseThrow(NoSuchElementException::new);

            return String.format("You got genre № %s: %s", id, genre.getName());
        } catch (NoSuchElementException e) {
            return String.format("Genre with id %s doesn't exist", id);
        }
    }

    @ShellMethod(value = "Add genre", key = {"ag", "add-g"})
    public String addGenre(String gName) {
        Genre genre = genreHandler.createGenre(gName);

        genre = genreService.save(genre);

        return String.format("You inserted genre № %s: %s", genre.getId(), genre.getName());
    }

    @ShellMethod(value = "Update genre", key = {"ug", "update-g"})
    public String updateGenre(String id, String gName) {
        try {
            Genre genre = genreService.updateById(id, gName);

            return String.format("You updated genre № %s: %s", genre.getId(), genre.getName());
        } catch (NoSuchElementException e) {
            return String.format("Genre with id %s doesn't exist", id);
        }
    }
}
