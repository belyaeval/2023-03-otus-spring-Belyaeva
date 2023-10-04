package ru.otus.springhw.dto.mapper;

import ru.otus.springhw.domain.Book;
import ru.otus.springhw.dto.BookDto;

public interface BookDtoMapper {
    BookDto toDto(Book book);

    Book fromDto(BookDto bookDto);

}
