package ru.otus.springhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.handler.BookCommentHandler;
import ru.otus.springhw.repository.BookCommentRepository;
import ru.otus.springhw.domain.BookComment;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentRepository commentRepository;

    private final BookService bookService;

    private final BookCommentHandler commentHandler;

    @Override
    public void save(BookComment comment) {
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void addComment(String bookId, String commText) {
        Book book = bookService.findById(bookId);
        BookComment comment = commentHandler.createComment(commText, book);

        save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllBookComments(String bookId) {
       List<BookComment> comms = commentRepository.getAllByBook(bookId);

        List<String> commsString = new ArrayList<>();

        for (BookComment comm : comms) {
            commsString.add(comm.getText());
        }

        return commsString;
    }
}
