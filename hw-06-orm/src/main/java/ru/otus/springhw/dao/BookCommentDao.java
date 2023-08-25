package ru.otus.springhw.dao;

import ru.otus.springhw.domain.BookComment;

import java.util.Optional;

public interface BookCommentDao {
    BookComment save(BookComment comment);

    Optional<BookComment> findById(long id);
}
