package ru.otus.springhw.service;

import ru.otus.springhw.dto.BookCommentsDto;

public interface BookCommentService {
    void addComment(Long bookId, String commText);

    BookCommentsDto getAllBookComments(Long bookId);
}
