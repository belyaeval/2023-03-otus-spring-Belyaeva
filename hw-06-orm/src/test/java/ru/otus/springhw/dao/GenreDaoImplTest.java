package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@DataJpaTest
@Import(GenreDaoImpl.class)
public class GenreDaoImplTest {
    @Autowired
    private GenreDao genreDao;

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(1, "роман");
        Optional<Genre> actualGenre = (genreDao.findById(expectedGenre.getId()));
        assertThat(actualGenre).isPresent();

        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertGenre() {
        Genre expectedGenre = new Genre("сказка");
        genreDao.save(expectedGenre);
        Optional<Genre> actualGenre = genreDao.findById(expectedGenre.getId());
        assertThat(actualGenre).isPresent();

        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
