package ru.otus.springhw.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@DataJpaTest
@Import(BookDaoImpl.class)
public class BookDaoImplTest {
    @Autowired
    private BookDaoImpl bookDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять книгу в БД")
    @Test
    public void shouldInsertBook() {
        Book expectedBook = new Book("Три толстяка", new Author(1, "Пушкин"), new Genre(1, "роман"));
        bookDao.save(expectedBook);

        Optional<Book> actualBook = (bookDao.findById(expectedBook.getId()));
        assertThat(actualBook).isPresent();

        assertThat(actualBook.get()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));
        Optional<Book> actualBook = (bookDao.findById(expectedBook.getId()));
        assertThat(actualBook).isPresent();

        assertThat(actualBook.get()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по названию")
    @Test
    void shouldReturnExpectedBookByName() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));
        Book actualBook = (bookDao.findByName(expectedBook.getName()));

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("обновлять книгу по id")
    @Test
    void shouldUpdateExpectedBookById() {
        Book expectedBook = new Book(1, "Три толстяка", new Author(2, "Олеша"), new Genre(2, "cказка"));
        Book actualBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "hоман"));

        bookDao.update(expectedBook);

        assertThat(actualBook).isNotEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу")
    @Test
    void shouldCorrectDeleteBookById() {
        Book book = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre(1, "роман"));

        assertThatCode(() -> bookDao.findById(book.getId())).doesNotThrowAnyException();

        bookDao.delete(em.merge(book));

        assertThat(bookDao.findById(book.getId())).isEqualTo(Optional.empty());
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        val books = bookDao.findAll();
        assertThat(books).isNotNull()
                .allMatch(b -> !b.getName().equals(""))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }
}
