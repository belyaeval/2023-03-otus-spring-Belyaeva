package ru.otus.springhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springhw.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
