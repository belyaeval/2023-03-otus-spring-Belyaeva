package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;

import java.util.List;

public interface BookCommentDao {
    List<BookComment> findAllByBook(Book book);
}
