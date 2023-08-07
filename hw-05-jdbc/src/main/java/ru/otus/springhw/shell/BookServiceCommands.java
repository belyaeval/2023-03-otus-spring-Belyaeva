package ru.otus.springhw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;
import ru.otus.springhw.handler.AuthorHandler;
import ru.otus.springhw.handler.BookHandler;
import ru.otus.springhw.handler.GenreHandler;
import ru.otus.springhw.service.BookService;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
    private final BookService bookService;

    private final AuthorHandler authorHandler;

    private final GenreHandler genreHandler;

    private final BookHandler bookHandler;

    @ShellMethod(value = "Get book", key = {"gb", "get-b"})
    public String getBook(long id) {
        Book book = bookService.getById(id);

        return book == null ? String.format("Book with id %d doesn't exists", id) :
                String.format("You got book № %d: %s, author: %s, genre: %s",
                        id, book.getName(), book.getAuthor().getName(), book.getGenre().getName());
    }

    @ShellMethod(value = "Add book", key = {"ab", "add-b"})
    public String addBook(String bName, String aName, String gName) {
        Author author = authorHandler.createAuthor(aName);
        Genre genre = genreHandler.createGenre(gName);
        Book book = bookHandler.createBook(bName, author, genre);

        book = bookService.insert(book);

        return book != null ? String.format("You inserted book № %d: %s, author: %s, genre: %s",
                book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName()) :
                "This book is already exists";
    }

    @ShellMethod(value = "Delete book", key = {"db", "delete-b"})
    public String deleteBook(long id) {
        Book book = bookService.deleteById(id);

        return String.format(
                "You deleted book № %d: %s, author: %s, genre: %s", book.getId(), book.getName(),
                book.getAuthor().getName(), book.getGenre().getName()
        );
    }

    @ShellMethod(value = "Update book", key = {"ub", "update-b"})
    public String updateBook(long id, String bName, String aName, String gName) {
        Author author = authorHandler.createAuthor(aName);
        Genre genre = genreHandler.createGenre(gName);
        Book book = bookHandler.createBook(id, bName, author, genre);

        book = bookService.update(book, id);

        return book == null ? String.format("Book with id %d doesn't exist", id) : String.format(
                "You updated book № %d: %s, author: %s, genre: %s", book.getId(), book.getName(),
                book.getAuthor().getName(), book.getGenre().getName()
        );
    }

    @ShellMethod(value = "Get all books", key = {"gab", "get-all-b"})
    public String getAll() {
        List<Book> books = bookService.getAll();

        List<String> booksString = new ArrayList<>();

        for (Book book : books) {
            booksString.add(bookHandler.convertToString(book));
        }

        return "Your books list: \n" + booksString;
    }
}
