package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@DataJpaTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoImplTest {
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(1, "Пушкин");
        Optional<Author> actualAuthor = (authorDao.findById(expectedAuthor.getId()));
        assertThat(actualAuthor).isPresent();

        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertAuthor() {
        Author expectedAuthor = new Author("Олеша");
        authorDao.save(expectedAuthor);
        Optional<Author> actualAuthor = authorDao.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isPresent();

        assertThat(actualAuthor.get()).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

}
