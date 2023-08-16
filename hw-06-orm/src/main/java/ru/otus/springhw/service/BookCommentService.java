package ru.otus.springhw.service;

import ru.otus.springhw.domain.BookComment;

import java.util.List;

public interface BookCommentService {
    List<BookComment> findAllByBookId(long id);
}
