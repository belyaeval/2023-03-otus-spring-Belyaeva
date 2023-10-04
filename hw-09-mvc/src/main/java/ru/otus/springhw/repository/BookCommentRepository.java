package ru.otus.springhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springhw.domain.BookComment;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

}
