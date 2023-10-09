package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.repository.AuthorRepository;
import ru.otus.springhw.domain.Author;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(Long id) {
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
    public Author updateById(Long id, String name) {
        Author authorToUpdate = findById(id).orElseThrow(NoSuchElementException::new);
        authorToUpdate.setName(name);

        return authorRepository.save(authorToUpdate);
    }
}
