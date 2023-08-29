package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.dao.BookCommentDaoImpl;
import ru.otus.springhw.domain.BookComment;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentDaoImpl commentDao;

    @Override
    @Transactional
    public void save(BookComment comment) {
        commentDao.save(comment);
    }
}
