package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.repository.AuthorRepository;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        Author existingAuthor = findByName(author.getName()).orElse(author);

        return authorRepository.save(existingAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author updateById(String id, String name) {
        Author authorToUpdate = findById(id).orElseThrow(NoSuchElementException::new);
        authorToUpdate.setName(name);

        List<Book> books = bookRepository.findAll();

        books.stream().filter((book) -> book.getAuthor().getId().equals(id))
                .forEach((book) -> {
                    book.setAuthor(authorToUpdate);
                    bookRepository.save(book);
                });

        return authorRepository.save(authorToUpdate);
    }
}
