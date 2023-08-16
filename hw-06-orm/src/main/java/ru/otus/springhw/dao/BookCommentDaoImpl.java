package ru.otus.springhw.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.springhw.domain.Book;
import ru.otus.springhw.domain.BookComment;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookCommentDaoImpl implements BookCommentDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<BookComment> findAllByBook(Book book) {
        long bookId = book.getId();

        EntityGraph<?> eg = em.getEntityGraph("book-comment-book-entity-graph");
        TypedQuery<BookComment> query = em.createQuery(
                "select c from BookComment c where c.book.id = :bookId", BookComment.class
        );
        query.setParameter("bookId", bookId);
        query.setHint("jakarta.persistence.fetchgraph", eg);

        return query.getResultList();
    }
}
