package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springhw.dao.AuthorDao;
import ru.otus.springhw.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author insert(Author author) {
        return authorDao.insert(author);
    }

    @Override
    public Author getByName(String name) {
        return authorDao.getByName(name);
    }
}
