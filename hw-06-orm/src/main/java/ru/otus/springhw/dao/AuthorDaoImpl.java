package ru.otus.springhw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springhw.domain.Author;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);

            return author;
        }

        return em.merge(author);
    }

    @Override
    public Author findByName(String name) {
        try {
            TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
            query.setParameter("name", name);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
