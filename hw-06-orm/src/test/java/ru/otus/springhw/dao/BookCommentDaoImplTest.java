package ru.otus.springhw.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с комментариями")
@DataJpaTest
@Import(BookCommentDaoImpl.class)
public class BookCommentDaoImplTest {
    @Autowired
    private BookCommentDaoImpl bookCommentDao;

    @DisplayName("возвращать ожидаемый список комментариев")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));

        val booksComms = bookCommentDao.findAllByBook(expectedBook);

        assertThat(booksComms).hasSize(2);
    }
}
