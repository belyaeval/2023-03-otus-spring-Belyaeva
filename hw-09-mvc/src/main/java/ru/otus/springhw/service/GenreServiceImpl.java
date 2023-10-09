package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.repository.GenreRepository;
import ru.otus.springhw.domain.Genre;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;


    @Override
    public Optional<Genre> findById(Long id) {
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
    public Genre updateById(Long id, String name) {
        Genre genreToUpdate = genreRepository.findById(id).orElseThrow(NoSuchElementException::new);
        genreToUpdate.setName(name);

        return genreRepository.save(genreToUpdate);
    }
}
