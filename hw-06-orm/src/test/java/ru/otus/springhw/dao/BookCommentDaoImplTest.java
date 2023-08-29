package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с комментариями")
@DataJpaTest
@Import(BookCommentDaoImpl.class)
public class BookCommentDaoImplTest {
    @Autowired
    private BookCommentDaoImpl bookCommentDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("сохранять комментарий в БД")
    @Test
    void shouldSaveBookComment() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));
        BookComment expectedComment = new BookComment("Супер", em.merge(expectedBook));
        bookCommentDao.save(expectedComment);
        Optional<BookComment> actualComment = bookCommentDao.findById(expectedComment.getId());
        assertThat(actualComment).isPresent();

        assertThat(actualComment.get()).usingRecursiveComparison().isEqualTo(expectedComment);
    }
}
