package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.dao.GenreDao;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;


    @Override
    public Genre findById(long id) {
        Optional<Genre> genre = genreDao.findById(id);

        return genre.orElse(null);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        Genre existedGenre = findByName(genre.getName());

        if (existedGenre == null) {
            return genreDao.save(genre);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findByName(String name) {
        return genreDao.findByName(name);
    }
}
