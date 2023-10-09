package ru.otus.springhw.handler;

import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;

@Component
public class BookCommentHandlerImpl implements BookCommentHandler {
    @Override
    public BookComment createComment(Long id, String text, Book book) {
        return new BookComment(id, text, book);
    }

    @Override
    public BookComment createComment(String text, Book book) {
        return new BookComment(text, book);
    }
}
