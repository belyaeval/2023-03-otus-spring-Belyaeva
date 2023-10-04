package ru.otus.springhw.dto.mapper;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Author;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.Genre;
import ru.otus.springhw.dto.BookDto;

@Component
public class BookDtoMapperImpl implements BookDtoMapper {
    @Override
    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName());
    }

    @Override
    public Book fromDto(BookDto bookDto) {
        return new Book(
                bookDto.getId(), bookDto.getName(), new Author(bookDto.getAuthor()), new Genre(bookDto.getGenre())
        );
    }
}
