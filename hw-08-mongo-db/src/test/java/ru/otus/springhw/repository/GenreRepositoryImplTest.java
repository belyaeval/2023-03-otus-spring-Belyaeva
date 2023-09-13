package ru.otus.springhw.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@DataMongoTest
public class GenreRepositoryImplTest {
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre("1", "повесть");
        Optional<Genre> actualGenre = (genreRepository.findById(expectedGenre.getId()));
        assertThat(actualGenre).isPresent();

        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertGenre() {
        Genre expectedGenre = new Genre("сказка");
        genreRepository.save(expectedGenre);
        Optional<Genre> actualGenre = genreRepository.findById(expectedGenre.getId());
        assertThat(actualGenre).isPresent();

        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
