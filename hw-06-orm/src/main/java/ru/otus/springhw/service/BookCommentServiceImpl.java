package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.dao.BookCommentDaoImpl;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentDaoImpl commentDao;

    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> findAllByBookId(long id) {
        Book book = bookService.findById(id);

        return commentDao.findAllByBook(book);
    }
}
