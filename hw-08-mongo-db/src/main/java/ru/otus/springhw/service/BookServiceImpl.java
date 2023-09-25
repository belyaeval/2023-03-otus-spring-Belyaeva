package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.repository.BookCommentRepository;
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

    private final BookCommentRepository commentRepository;

    @Override
    @Transactional
    public Book save(Book book) {
        Book existingBook = findByName(book.getName()).orElse(null);

        if (existingBook == null) {
            validateBook(book);

            return bookRepository.save(book);
        }

        String existingBookAName = existingBook.getAuthor().getName();
        String newBookAName = book.getAuthor().getName();

        if (existingBookAName.equals(newBookAName)) {
            throw new RuntimeException();
        }

        validateBook(book);

        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book updateById(String id, Book book) {
        findById(id).orElseThrow(NoSuchElementException::new);

        validateBook(book);
        book.setId(id);

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Book bookToDelete = findById(id).orElseThrow(NoSuchElementException::new);

        List<BookComment> comms = commentRepository.getAllByBook(id);

        if (!comms.isEmpty()) {
            comms.forEach(commentRepository::delete);
        }

        bookRepository.delete(bookToDelete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    private Author validateAuthor(Author author) {
        return authorService.findByName(author.getName()).orElseGet(() -> authorService.save(author));
    }

    private Genre validateGenre(Genre genre) {
        return genreService.findByName(genre.getName()).orElseGet(() -> genreService.save(genre));
    }

    private void validateBook(Book book) {
        Author author = validateAuthor(book.getAuthor());
        Genre genre = validateGenre(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);
    }
}
