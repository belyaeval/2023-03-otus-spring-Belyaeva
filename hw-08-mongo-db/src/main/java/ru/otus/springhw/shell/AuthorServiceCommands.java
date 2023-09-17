package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.handler.AuthorHandler;
import ru.otus.springhw.service.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceCommands {
    private final AuthorService authorService;

    private final AuthorHandler authorHandler;

    @ShellMethod(value = "Get author", key = {"ga", "get-a"})
    public String getAuthor(String id) {
        try {
            Author author = authorService.findById(id).orElseThrow(NoSuchElementException::new);

            return String.format("You got author № %s: %s", id, author.getName());
        } catch (NoSuchElementException e) {
            return String.format("Author with id %s doesn't exist", id);
        }
    }

    @ShellMethod(value = "Add author", key = {"aa", "add-a"})
    public String addAuthor(String aName) {
        Author author = authorHandler.createAuthor(aName);
        author = authorService.save(author);

        return String.format("You inserted author № %s: %s", author.getId(), author.getName());
    }

    @ShellMethod(value = "Get all authors", key = {"gaa", "get-all-a"})
    public String getAll() {
        List<Author> authors = authorService.findAll();

        List<String> aString = new ArrayList<>();

        for (Author a : authors) {
            aString.add(authorHandler.convertToString(a));
        }

        return "Your authors list: \n" + aString;
    }

    @ShellMethod(value = "Update author", key = {"ua", "update-a"})
    public String updateAuthor(String id, String aName) {
       try {
            Author author = authorService.updateById(id, aName);

            return String.format("You updated author № %s: %s", author.getId(), author.getName());
        } catch (NoSuchElementException e) {
            return String.format("Author with id %s doesn't exist", id);
        }
    }
}
