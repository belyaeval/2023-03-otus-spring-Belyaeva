package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.handler.AuthorHandler;
import ru.otus.springhw.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceCommands {
    private final AuthorService authorService;

    private final AuthorHandler authorHandler;

    @ShellMethod(value = "Get author", key = {"ga", "get-a"})
    public String getAuthor(String id) {
        Author author = authorService.findById(id);

        return author == null ? String.format("Author with id %s doesn't exist", id) :
                String.format("You got author № %s: %s", id, author.getName());
    }

    @ShellMethod(value = "Add author", key = {"aa", "add-a"})
    public String addAuthor(String aName) {
        Author author = authorHandler.createAuthor(aName);

        author = authorService.save(author);

        return author == null ? String.format("Author '%s' is already exists", aName) :
                String.format("You inserted author № %s: %s", author.getId(), author.getName());
    }
}
