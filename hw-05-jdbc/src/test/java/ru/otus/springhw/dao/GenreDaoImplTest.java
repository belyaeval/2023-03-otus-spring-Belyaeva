package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoImpl.class)
public class GenreDaoImplTest {
    @Autowired
    private GenreDao genreDao;

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(1, "роман");
        Genre actualGenre = genreDao.getById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertGenre() {
        Genre expectedGenre = new Genre("сказка");
        genreDao.insert(expectedGenre);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
