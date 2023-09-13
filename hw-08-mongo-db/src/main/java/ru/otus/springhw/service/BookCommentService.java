package ru.otus.springhw.service;

import ru.otus.springhw.domain.BookComment;

import java.util.List;

public interface BookCommentService {
    void save(BookComment comment);

    void addComment(String bookId, String commText);

    List<String> getAllBookComments(String bookId);
}
