package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springhw.dao.BookDao;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public Book insert(Book book) {
        Book existingBook = getByName(book.getName());
        Book validatedBook;

        if (existingBook == null) {
            validatedBook = validateBook(book);

            return bookDao.insert(validatedBook);
        }

        String existingBookAName = existingBook.getAuthor().getName();
        String newBookAName = book.getAuthor().getName();
        String existingBookGName = existingBook.getGenre().getName();
        String newBookGName = book.getGenre().getName();

        if (existingBookAName.equals(newBookAName) && existingBookGName.equals(newBookGName)) {
            return null;
        }

        validatedBook = validateBook(book);

        return bookDao.insert(validatedBook);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Book update(Book book, long id) {
        validateBook(book);

        return bookDao.update(book, id);
    }

    @Override
    public Book deleteById(long id) {
        return bookDao.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getByName(String name) {
        return bookDao.getByName(name);
    }

    private Author validateAuthor(Author author) {
        Author validatedAuthor = authorService.insert(author);

        if (validatedAuthor == null) {
            validatedAuthor = author.getId() == 0 ? authorService.getByName(author.getName()) :
                    authorService.getById(author.getId());
        }

        return validatedAuthor;
    }

    private Genre validateGenre(Genre genre) {
        Genre validatedGenre = genreService.insert(genre);

        if (validatedGenre == null) {
            validatedGenre = genre.getId() == 0 ? genreService.getByName(genre.getName()) :
                    genreService.getById(genre.getId());
        }

        return validatedGenre;
    }

    private Book validateBook(Book book) {
        Author author = validateAuthor(book.getAuthor());
        Genre genre = validateGenre(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);

        return book;
    }
}
