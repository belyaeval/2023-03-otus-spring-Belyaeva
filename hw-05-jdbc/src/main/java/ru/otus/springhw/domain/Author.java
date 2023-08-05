package ru.otus.springhw.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private long id;

    private String name;

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }
}
