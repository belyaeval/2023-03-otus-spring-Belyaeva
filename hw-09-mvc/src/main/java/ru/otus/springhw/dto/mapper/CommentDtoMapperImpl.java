package ru.otus.springhw.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.dto.CommentDto;

@Component
@RequiredArgsConstructor
public class CommentDtoMapperImpl implements CommentDtoMapper {
    @Override
    public CommentDto toDto(BookComment comment) {
        return new CommentDto(
                comment.getId(), comment.getText(), comment.getBook().getId(), comment.getBook().getName())
                ;
    }

    @Override
    public BookComment fromDto(CommentDto commentDto) {
        return new BookComment(
                commentDto.getId(), commentDto.getText(), new Book(commentDto.getId(), commentDto.getBookName())
        );
    }
}
