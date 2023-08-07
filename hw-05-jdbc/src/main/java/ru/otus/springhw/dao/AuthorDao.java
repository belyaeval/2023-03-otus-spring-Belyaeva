package ru.otus.springhw.dao;

import ru.otus.springhw.domain.Author;

public interface AuthorDao {
    Author getById(long id);

    Author insert(Author author);

    Author getByName(String name);
}
