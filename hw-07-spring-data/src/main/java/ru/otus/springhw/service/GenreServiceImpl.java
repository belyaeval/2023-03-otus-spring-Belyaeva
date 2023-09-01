package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.repository.GenreRepository;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;


    @Override
    public Genre findById(long id) {
        Optional<Genre> genre = genreRepository.findById(id);

        return genre.orElse(null);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        Genre existedGenre = findByName(genre.getName());

        if (existedGenre == null) {
            return genreRepository.save(genre);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findByName(String name) {
        Optional<Genre> genre = genreRepository.findByName(name);

        return genre.orElse(null);
    }
}
