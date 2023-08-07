package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoImplTest {
    @Autowired
    private AuthorDao authorDao;

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(1, "Пушкин");
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertAuthor() {
        Author existingAuthor = new Author("Пушкин");

        assertThat(authorDao.insert(existingAuthor)).isEqualTo(null);
    }

}
