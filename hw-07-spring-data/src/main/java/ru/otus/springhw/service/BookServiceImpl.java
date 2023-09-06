package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.repository.BookRepository;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    @Transactional
    public Book save(Book book) {
        Book existingBook = findByName(book.getName());

        if (existingBook == null) {
            validateBook(book);

            return bookRepository.save(book);
        }

        String existingBookAName = existingBook.getAuthor().getName();
        String newBookAName = book.getAuthor().getName();

        if (existingBookAName.equals(newBookAName)) {
            throw new NoSuchElementException();
        }

        validateBook(book);

        return bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        Optional<Book> book = bookRepository.findById(id);

        return book.orElse(null);
    }

    @Override
    @Transactional
    public Book updateById(long id, Book book) {
        Book bookToUpdate = findById(id);

        if (bookToUpdate != null) {
            validateBook(book);
            book.setId(id);

            return bookRepository.save(book);
        }

        throw new NoSuchElementException();
    }

    @Override
    @Transactional
    public Book deleteById(long id) {
        Book bookToDelete = findById(id);

        if (bookToDelete != null) {
            bookRepository.delete(bookToDelete);

            return bookToDelete;
        }

        return null;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByName(String name) {
        Optional<Book> book = bookRepository.findByName(name);

        return book.orElse(null);
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
