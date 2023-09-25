package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.repository.BookRepository;
import ru.otus.springhw.repository.GenreRepository;
import ru.otus.springhw.domain.Genre;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        Genre existingGenre = findByName(genre.getName()).orElse(genre);

        return genreRepository.save(existingGenre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    @Transactional
    public Genre updateById(String id, String name) {
        Genre genreToUpdate = genreRepository.findById(id).orElseThrow(NoSuchElementException::new);
        genreToUpdate.setName(name);

        List<Book> books = bookRepository.findAll();

        books.stream().filter((book) -> book.getGenre().getId().equals(id))
                .forEach((book) -> {
                    book.setGenre(genreToUpdate);
                    bookRepository.save(book);
                });

        return genreRepository.save(genreToUpdate);
    }
}
