package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.dao.BookDao;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;


    @Override
    @Transactional
    public Book save(Book book) {
        Book existingBook = findByName(book.getName());

        if (existingBook == null) {
            validateBook(book);

            return bookDao.save(book);
        }

        String existingBookAName = existingBook.getAuthor().getName();
        String newBookAName = book.getAuthor().getName();
        String existingBookGName = existingBook.getGenre().getName();
        String newBookGName = book.getGenre().getName();

        if (existingBookAName.equals(newBookAName) && existingBookGName.equals(newBookGName)) {
            return null;
        }

        validateBook(book);

        return bookDao.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        Optional<Book> book = bookDao.findById(id);

        return book.orElse(null);
    }

    @Override
    @Transactional
    public Book update(Book book, long id) {
        Book bookToUpdate = findById(id);

        if (bookToUpdate != null) {
            validateBook(book);
            book.setId(id);

            return bookDao.update(book);
        }
        return null;
    }

    @Override
    @Transactional
    public Book deleteById(long id) {
        Book bookToDelete = findById(id);

        if (bookToDelete != null) {
            bookDao.delete(bookToDelete);

            return bookToDelete;
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByName(String name) {
        return bookDao.findByName(name);
    }

    private Author validateAuthor(Author author) {
        Author validatedAuthor = author.getId() == 0 ? authorService.findByName(author.getName()) :
                authorService.findById(author.getId());

        return validatedAuthor == null ? authorService.save(author) : validatedAuthor;
    }

    private Genre validateGenre(Genre genre) {
        Genre validatedGenre = genre.getId() == 0 ? genreService.findByName(genre.getName()) :
                genreService.findById(genre.getId());

        return validatedGenre == null ? genreService.save(genre) : validatedGenre;
    }

    private void validateBook(Book book) {
        Author author = validateAuthor(book.getAuthor());
        Genre genre = validateGenre(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);
    }
}
