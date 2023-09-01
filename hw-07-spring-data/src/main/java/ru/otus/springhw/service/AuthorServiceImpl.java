package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.repository.AuthorRepository;
import ru.otus.springhw.domain.Author;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findById(long id) {
        Optional<Author> author = authorRepository.findById(id);

        return author.orElse(null);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        Author existedAuthor = findByName(author.getName());

        if (existedAuthor == null) {
            return authorRepository.save(author);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Author findByName(String name) {
        Optional<Author> author = authorRepository.findByName(name);

        return author.orElse(null);
    }
}
