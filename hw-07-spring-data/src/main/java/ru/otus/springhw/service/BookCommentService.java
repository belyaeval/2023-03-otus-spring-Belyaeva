package ru.otus.springhw.service;

import ru.otus.springhw.domain.BookComment;

import java.util.List;

public interface BookCommentService {
    void save(BookComment comment);

    void addComment(long bookId, String commText);

    List<String> getAllBookComments(long bookId);
}
