package ru.otus.springhw.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с комментариями должен")
@DataMongoTest
public class BookCommentRepositoryTest {
    @Autowired
    private BookCommentRepository bookCommentRepository;

    @DisplayName("сохранять комментарий в БД")
    @Test
    void shouldSaveBookComment() {
        Book expectedBook = new Book("1", "Метель", new Author("1", "Пушкин"), new Genre("1", "повесть"));
        BookComment expectedComment = new BookComment("Супер", expectedBook);
        bookCommentRepository.save(expectedComment);
        Optional<BookComment> actualComment = bookCommentRepository.findById(expectedComment.getId());
        assertThat(actualComment).isPresent();

        assertThat(actualComment.get()).usingRecursiveComparison().isEqualTo(expectedComment);
    }
}
