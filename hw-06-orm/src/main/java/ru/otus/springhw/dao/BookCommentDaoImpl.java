package ru.otus.springhw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.BookComment;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookCommentDaoImpl implements BookCommentDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public BookComment save(BookComment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);

            return comment;
        }

        return em.merge(comment);
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }
}
