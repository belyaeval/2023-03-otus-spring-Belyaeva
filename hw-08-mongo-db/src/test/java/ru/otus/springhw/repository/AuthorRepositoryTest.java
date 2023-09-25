package ru.otus.springhw.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.springhw.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами должен")
@DataMongoTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author("1", "Пушкин");
        Optional<Author> actualAuthor = (authorRepository.findById(expectedAuthor.getId()));
        assertThat(actualAuthor).isPresent();

        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertAuthor() {
        Author expectedAuthor = new Author("Олеша");
        authorRepository.save(expectedAuthor);
        Optional<Author> actualAuthor = authorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isPresent();

        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

}
