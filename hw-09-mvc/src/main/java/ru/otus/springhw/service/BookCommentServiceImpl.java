package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.dto.BookCommentsDto;
import ru.otus.springhw.dto.CommentDto;
import ru.otus.springhw.dto.mapper.CommentDtoMapper;
import ru.otus.springhw.exception.NotFoundException;
import ru.otus.springhw.handler.BookCommentHandler;
import ru.otus.springhw.repository.BookCommentRepository;
import ru.otus.springhw.domain.BookComment;
import ru.otus.springhw.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentRepository commentRepository;

    private final BookRepository bookRepo;

    private final BookCommentHandler commentHandler;

    private final CommentDtoMapper commentDtoMapper;

    @Override
    @Transactional
    public void addComment(Long bookId, String commText) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
        BookComment comment = commentHandler.createComment(commText, book);

        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public BookCommentsDto getAllBookComments(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));

        List<CommentDto> commentsDto = book.getComments().stream().map(commentDtoMapper::toDto).toList();

        return new BookCommentsDto(book.getId(), book.getName(), commentsDto);
    }
}
