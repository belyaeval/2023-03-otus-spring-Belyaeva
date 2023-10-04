package ru.otus.springhw.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.springhw.dto.BookDto;
import ru.otus.springhw.service.BookService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Controller для работы с книгами должен проверять, что")
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("выводится список всех книг")
    public void shouldReturnBookList() throws Exception {
        List<BookDto> books = List.of(new BookDto(1, "Евгений Онегин", "Пушкин", "роман"));
        given(bookService.findAll()).willReturn(books);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", books))
                .andExpect(view().name("list"));
    }

    @Test
    @DisplayName("выводится книга по id")
    public void shouldFindBookById() throws Exception {
        BookDto expectedBook = new BookDto(1, "Евгений Онегин", "Пушкин", "роман");
        given(bookService.findById(1L)).willReturn(expectedBook);

        mockMvc.perform(get("/edit").param("id", String.valueOf(expectedBook.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", expectedBook))
                .andExpect(view().name("edit-book"));
    }

    @Test
    @DisplayName("книга обновляется по id")
    public void shouldUpdateBookById() throws Exception {
        BookDto expectedBookDto = new BookDto("Метель", "Пушкин", "повесть");
        BookDto newBookDto = new BookDto(1, "Метель", "Пушкин", "повесть");

        given(bookService.updateById(1, expectedBookDto)).willReturn(newBookDto);

        mockMvc.perform(post("/edit")
                        .param("id", "1")
                        .param("name", expectedBookDto.getName())
                        .param("author", expectedBookDto.getAuthor())
                        .param("genre", expectedBookDto.getGenre()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("удаляется книга по id")
    public void shouldDeleteBookById() throws Exception {
        mockMvc.perform(post("/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("выводится форма для добавления книги")
    public void shouldReturnBookForm() throws Exception {
        mockMvc.perform(get("/add-book"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("add-book"));
    }

    @Test
    @DisplayName("книга добавляется в бд")
    public void shouldAddBook() throws Exception {
        BookDto expectedBookDto = new BookDto("Метель", "Пушкин", "повесть");

        given(bookService.add(expectedBookDto)).willReturn(expectedBookDto);

        mockMvc.perform(post("/add-book")
                        .param("name", expectedBookDto.getName())
                        .param("author", expectedBookDto.getAuthor())
                        .param("genre", expectedBookDto.getGenre()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
