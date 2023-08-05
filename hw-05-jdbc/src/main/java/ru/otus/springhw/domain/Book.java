package ru.otus.springhw.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private long id;

    private String name;

    private Author author;

    private Genre genre;

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
