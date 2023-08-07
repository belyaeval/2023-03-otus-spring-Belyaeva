package ru.otus.springhw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springhw.dao.BookDao;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Service для рабобты с книгами должен проверять")
@SpringBootTest
public class BookServiceImplTest {
    @MockBean
    private BookDao bookDao;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("что книга добавлена")
    @Test
    public void shouldAddNewBook() {
        Book expectedBook = new Book("Три толстяка", new Author( "Олеша"), new Genre( "Сказка"));
        Author expectedAuthor = expectedBook.getAuthor();
        Genre expectedGenre = expectedBook.getGenre();

        given(authorService.insert(expectedAuthor)).willReturn(expectedAuthor);
        given(genreService.insert(expectedGenre)).willReturn(expectedGenre);
        given(bookDao.insert(expectedBook)).willReturn(expectedBook);

        bookService.insert(expectedBook);

        verify(bookDao).insert(expectedBook);
    }

    @DisplayName("что взята ожидаемая книга по id")
    @Test
    public void shouldReturnBookById() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман"));

        given(bookDao.getById(1)).willReturn(expectedBook);

        bookService.getById(1);

        verify(bookDao, times(1)).getById(1);
    }

    @DisplayName("что книга обновлена по id")
    @Test
    public void shouldReturnUpdatedBook() {
        Book expectedBook = new Book("Три толстяка", new Author( "Олеша"), new Genre( "Сказка"));
        long id = 1;

        given(bookDao.update(expectedBook, id)).willReturn(expectedBook);

        bookService.update(expectedBook, id);

        verify(bookDao).update(expectedBook, id);
    }

    @DisplayName("что книга удалена по id")
    @Test
    public void shouldDeleteBookById() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман"));
        long id = 1;

        given(bookDao.deleteById(id)).willReturn(expectedBook);

        bookService.deleteById(id);

        verify(bookDao).deleteById(id);
    }

    @DisplayName("что выводится список всех книг")
    @Test
    public void shouldReturnAllBooks() {
        List<Book> books = List.of(new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман")));

        given(bookDao.getAll()).willReturn(books);

        bookService.getAll();

        verify(bookDao).getAll();
    }


}
