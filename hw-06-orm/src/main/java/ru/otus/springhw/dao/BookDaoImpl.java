package ru.otus.springhw.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);

            return book;
        }

        return em.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        EntityGraph<?> eg = em.getEntityGraph("book-author-genre-entity-graph");

        return Optional.ofNullable(em.find(Book.class, id, Map.of("jakarta.persistence.fetchgraph", eg)));
    }

    @Override
    public Book update(Book book) {
        return em.merge(book);
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> eg = em.getEntityGraph("book-author-genre-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("jakarta.persistence.fetchgraph", eg);

        return query.getResultList();
    }

    @Override
    public Book findByName(String name) {
        try {
            EntityGraph<?> eg = em.getEntityGraph("book-author-genre-entity-graph");
            TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
            query.setParameter("name", name);
            query.setHint("jakarta.persistence.fetchgraph", eg);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
