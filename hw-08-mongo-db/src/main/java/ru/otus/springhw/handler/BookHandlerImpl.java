package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

@Component
public class BookHandlerImpl implements BookHandler {

    @Override
    public Book createBook(String id, String bookName, Author author, Genre genre) {
        return new Book(id, bookName, author, genre);
    }

    @Override
    public Book createBook(String bookName, Author author, Genre genre) {
        return new Book(bookName, author, genre);
    }

    @Override
    public String convertToString(Book book) {
        return "№ " + book.getId() + " , название: " + book.getName() +
                " , автор: " + book.getAuthor().getName() + " , жанр: " + book.getGenre().getName() + ";";
    }
}
