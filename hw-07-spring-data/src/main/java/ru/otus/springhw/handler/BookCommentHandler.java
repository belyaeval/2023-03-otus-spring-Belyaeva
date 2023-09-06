package ru.otus.springhw.handler;

import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;

public interface BookCommentHandler {
    BookComment createComment(long id, String text, Book book);

    BookComment createComment(String text, Book book);
}
