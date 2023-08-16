package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.dao.AuthorDao;
import ru.otus.springhw.domain.Author;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    @Transactional(readOnly = true)
    public Author findById(long id) {
        Optional<Author> author = authorDao.findById(id);

        return author.orElse(null);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        Author existedAuthor = findByName(author.getName());

        if (existedAuthor == null) {
            return authorDao.save(author);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Author findByName(String name) {
        return authorDao.findByName(name);
    }
}
