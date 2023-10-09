package ru.otus.springhw.dto.mapper;

import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.dto.CommentDto;

public interface CommentDtoMapper {
    CommentDto toDto(BookComment comment);

    BookComment fromDto(CommentDto commentDto);
}
