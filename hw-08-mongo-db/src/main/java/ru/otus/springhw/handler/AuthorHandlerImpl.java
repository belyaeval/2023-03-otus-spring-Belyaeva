package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Author;

@Component
public class AuthorHandlerImpl implements AuthorHandler {
    @Override
    public Author createAuthor(String id, String name) {
        return new Author(id, name);
    }

    @Override
    public Author createAuthor(String name) {
        return new Author(name);
    }

    @Override
    public String convertToString(Author author) {
        return "№ " + author.getId() + " , имя: " + author.getName();
    }
}
