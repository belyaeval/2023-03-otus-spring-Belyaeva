package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springhw.dao.GenreDao;
import ru.otus.springhw.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre insert(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }
}
