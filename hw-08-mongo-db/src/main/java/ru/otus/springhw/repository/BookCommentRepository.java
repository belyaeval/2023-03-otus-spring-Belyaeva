package ru.otus.springhw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springhw.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends MongoRepository<BookComment, String> {
    List<BookComment> getAllByBook(String bookId);

}
