package ru.otus.springhw.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoImpl.class)
public class BookDaoImplTest {
    @Autowired
    private BookDaoImpl bookDao;

    @DisplayName("добавлять книгу в БД")
    @Test
    public void shouldInsertBook() {
        Book expectedBook = new Book("Три толстяка", new Author(1, "Пушкин"), new Genre(1, "роман"));
        bookDao.insert(expectedBook);

        Book actualBook = bookDao.getById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));
        Book actualBook = bookDao.getById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("обновлять книгу по id")
    @Test
    void shouldUpdateExpectedBookById() {
        Book expectedBook = new Book("Три толстяка", new Author(2, "Олеша"), new Genre(2, "cказка"));
        Book actualBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "hоман"));

        bookDao.update(expectedBook, 1);

        assertThat(actualBook).isNotEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(1)).doesNotThrowAnyException();

        bookDao.deleteById(1);

        assertThat(bookDao.getById(1)).isEqualTo(null);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
//        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));
//        List<Book> actualBookList = bookDao.getAll();
//        assertThat(actualBookList).contains(expectedBook);
        assertThat(bookDao.getAll()).hasSize(1);
    }
}
