package ru.otus.springhw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springhw.repository.BookRepository;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Service для работы с книгами должен проверять")
@SpringBootTest
public class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;

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

        given(authorService.save(expectedAuthor)).willReturn(expectedAuthor);
        given(genreService.save(expectedGenre)).willReturn(expectedGenre);
        given(bookRepository.save(expectedBook)).willReturn(expectedBook);

        bookService.save(expectedBook);

        verify(bookRepository).save(expectedBook);
    }

    @DisplayName("что взята ожидаемая книга по id")
    @Test
    public void shouldReturnBookById() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман"));

        given(bookRepository.findById(1)).willReturn(Optional.of(expectedBook));

        bookService.findById(1);

        verify(bookRepository, times(1)).findById(1);
    }

    @DisplayName("что книга обновлена по id")
    @Test
    public void shouldReturnUpdatedBook() {
        Book expectedBook = new Book(1,"Три толстяка", new Author( "Олеша"), new Genre( "сказка"));
        Book givenBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман"));

        given(bookRepository.findById(1)).willReturn(Optional.of(givenBook));

        bookService.updateById(expectedBook.getId(), expectedBook);

        verify(bookRepository).save(expectedBook);
    }

    @DisplayName("что книга удалена по id")
    @Test
    public void shouldDeleteBookById() {
        Book expectedBook = new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман"));
        long id = 1;

        given(bookRepository.findById(1)).willReturn(Optional.of(expectedBook));

        bookService.deleteById(id);

        verify(bookRepository).delete(expectedBook);
    }

    @DisplayName("что выводится список всех книг")
    @Test
    public void shouldReturnAllBooks() {
        List<Book> books = List.of(new Book(1, "Евгений Онегин", new Author(1, "Пушкин"), new Genre("Роман")));

        given(bookRepository.findAll()).willReturn(books);

        bookService.findAll();

        verify(bookRepository).findAll();
    }


}
